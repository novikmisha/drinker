package com.noverin.drinker.service.twitch

interface CacheableTwitchService : TwitchService {

    fun evictCache(username: String)
}