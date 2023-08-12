package com.noverin.drinker.adapter.`in`.twitch

import com.github.twitch4j.events.ChannelGoOfflineEvent
import com.noverin.drinker.infrastructure.properties.TwitchProperties
import com.noverin.drinker.service.usecase.EvictStreamCacheUseCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class StreamGoOfflineHandler(
    val evictStreamCacheUseCase: EvictStreamCacheUseCase,
    val twitchProperties: TwitchProperties
) {

    private val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

    fun onEvent(event: ChannelGoOfflineEvent) {
        logger.info("stream ${twitchProperties.streamerUsername} went offline")
        evictStreamCacheUseCase()
    }
}