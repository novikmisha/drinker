package com.noverin.drinker.service.usecase

import com.noverin.drinker.infrastructure.properties.TwitchProperties
import com.noverin.drinker.service.twitch.CacheableTwitchService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class EvictStreamCacheUseCase(
    @Lazy
    val cacheableTwitchService: CacheableTwitchService,
    val twitchProperties: TwitchProperties
) {

    operator fun invoke() {
        cacheableTwitchService.evictCache(twitchProperties.streamerUsername)
    }
}