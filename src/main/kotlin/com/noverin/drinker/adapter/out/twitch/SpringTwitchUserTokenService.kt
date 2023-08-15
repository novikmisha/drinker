package com.noverin.drinker.adapter.out.twitch

import com.noverin.drinker.domain.TwitchUserToken
import com.noverin.drinker.service.repository.TwitchUserTokenRepository
import com.noverin.drinker.service.twitch.TwitchUserTokenService
import org.springframework.security.oauth2.client.*
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.security.oauth2.core.OAuth2RefreshToken
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionTemplate

@Component
class SpringTwitchUserTokenService(
    private val clientRegistrationRepository: ClientRegistrationRepository,
    val twitchUserTokenRepository: TwitchUserTokenRepository,
    val transactionTemplate: TransactionTemplate
) : TwitchUserTokenService {

    private val clientManager = AuthorizedClientServiceOAuth2AuthorizedClientManager(
        clientRegistrationRepository, InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository))
        .apply {
            setAuthorizedClientProvider(OAuth2AuthorizedClientProviderBuilder.builder()
                .refreshToken()
                .build())
        }

    override fun withUserTokens(userId: String, apply: (tokens: TwitchUserToken) -> Unit) {
        transactionTemplate.execute {
            val twitchUserToken = twitchUserTokenRepository.findForUpdate(userId)
                ?: error("tokens not found for user $userId")

            val clientRegistration = clientRegistrationRepository.findByRegistrationId("twitch")
            val authorizedClient = getAuthorizedClient(clientRegistration, twitchUserToken)
            val authorizeRequest = OAuth2AuthorizeRequest.withAuthorizedClient(authorizedClient)
                .principal("twitch")
                .build()

            clientManager.authorize(authorizeRequest)?.let { authorize ->
                if (!authorize.accessToken.tokenValue.equals(twitchUserToken.accessToken)) {
                    twitchUserToken.apply {
                        accessToken = authorize.accessToken.tokenValue
                        ttl = authorize.accessToken.expiresAt!!
                        twitchUserTokenRepository.save(this)
                    }
                }
            }

            twitchUserToken
        }?.let(apply)
    }

    private fun getAuthorizedClient(clientRegistration: ClientRegistration, twitchUserToken: TwitchUserToken) =
        OAuth2AuthorizedClient(
            clientRegistration,
            "twitch",
            OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                twitchUserToken.accessToken,
                null,
                twitchUserToken.ttl,
                clientRegistration.scopes
            ),
            OAuth2RefreshToken(
                twitchUserToken.refreshToken,
                null,
                null
            )
        )
}