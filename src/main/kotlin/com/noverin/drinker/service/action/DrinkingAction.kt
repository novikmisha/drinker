package com.noverin.drinker.service.action

import com.noverin.drinker.domain.DrinkerContext
import com.noverin.drinker.infrastructure.properties.TwitchProperties
import com.noverin.drinker.infrastructure.util.drinker
import com.noverin.drinker.service.twitch.TwitchChatService
import com.noverin.drinker.service.twitch.TwitchUserTokenService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DrinkingAction(
    val twitchChatService: TwitchChatService,
    val twitchProperties: TwitchProperties,
    val twitchUserTokenService: TwitchUserTokenService
) : AbstractAction() {

    private val drinkingCommand = "!смузи"

    @Transactional
    override fun execute(context: DrinkerContext) {
        val userId = context.drinker().id
        twitchUserTokenService.withUserTokens(userId) { tokens ->
            twitchChatService.messageInChat(twitchProperties.streamerUsername, tokens.accessToken, drinkingCommand)
        }
    }
}