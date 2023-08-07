package com.noverin.drinker.service.action

import com.noverin.drinker.domain.DrinkerContext
import com.noverin.drinker.infrastructure.properties.TwitchProperties
import com.noverin.drinker.infrastructure.util.drinker
import com.noverin.drinker.service.repository.TwitchUserRepository
import com.noverin.drinker.service.twitch.TwitchService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DrinkingAction(
    val twitchService: TwitchService,
    val twitchProperties: TwitchProperties,
    val twitchUserRepository: TwitchUserRepository
) : AbstractAction() {

    private val drinkingCommand = "!смузи"
    private val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

    override fun execute(context: DrinkerContext) {
        runCatching {

            val username = context.drinker().id
            val user = twitchUserRepository.findByUsername(username)

            twitchService.messageInChat(twitchProperties.streamerUsername, user, drinkingCommand)
        }.onFailure {
            logger.error("can't drink by ${context.drinker().id}", it)
        }
    }
}