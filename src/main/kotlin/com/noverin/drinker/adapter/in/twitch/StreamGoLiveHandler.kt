package com.noverin.drinker.adapter.`in`.twitch

import com.github.twitch4j.events.ChannelGoLiveEvent
import com.noverin.drinker.infrastructure.properties.TwitchProperties
import com.noverin.drinker.service.usecase.StartDrinkUseCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class StreamGoLiveHandler(
    val drinkUseCase: StartDrinkUseCase,
    val twitchProperties: TwitchProperties
) {

    private val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

    fun onEvent(event: ChannelGoLiveEvent) {
        logger.info("stream ${twitchProperties.streamerUsername} went live")
        drinkUseCase()
    }
}