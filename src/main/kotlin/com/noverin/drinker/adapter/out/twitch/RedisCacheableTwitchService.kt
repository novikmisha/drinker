package com.noverin.drinker.adapter.out.twitch

import com.noverin.drinker.service.twitch.CacheableTwitchService
import com.noverin.drinker.service.twitch.TwitchService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class RedisCacheableTwitchService(
    val twitchService: TwitchService
) : CacheableTwitchService {

    @Cacheable(value = ["streamOnline"])
    override fun isStreamOnline(username: String) =
        twitchService.isStreamOnline(username)

    @CacheEvict(value = ["streamOnline"])
    override fun evictCache(username: String) {
    }
}