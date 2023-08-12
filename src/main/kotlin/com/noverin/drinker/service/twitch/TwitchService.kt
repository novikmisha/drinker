package com.noverin.drinker.service.twitch


interface TwitchService {
    fun isStreamOnline(username: String): Boolean
}