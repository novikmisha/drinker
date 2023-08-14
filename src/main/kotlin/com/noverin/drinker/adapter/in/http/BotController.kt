package com.noverin.drinker.adapter.`in`.http

import com.noverin.drinker.service.usecase.DisableBotUseCase
import com.noverin.drinker.service.usecase.EnableBotUseCase
import com.noverin.drinker.service.usecase.model.DisableBotCommand
import com.noverin.drinker.service.usecase.model.EnableBotCommand
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class BotController(
    val enableBotUseCase: EnableBotUseCase,
    val disableBotUseCase: DisableBotUseCase
) {

    @PostMapping("/bot")
    fun enableBot(@RegisteredOAuth2AuthorizedClient("twitch") authorizedClient: OAuth2AuthorizedClient,
                  principal: Principal) {
        enableBotUseCase(
            EnableBotCommand(principal.name,
                authorizedClient.accessToken.tokenValue,
                authorizedClient.accessToken.expiresAt ?: error("no ttl for access token"),
                authorizedClient.refreshToken?.tokenValue ?: error("no refresh token"))
        )
    }

    @DeleteMapping("/bot")
    fun disableBot(principal: Principal) {
        disableBotUseCase(
            DisableBotCommand(principal.name)
        )
    }

}
