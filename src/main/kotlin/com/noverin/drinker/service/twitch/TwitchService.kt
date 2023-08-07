package com.noverin.drinker.service.twitch

import com.noverin.drinker.domain.TwitchUser

interface TwitchService {

    fun isStreamOnline(username: String) : Boolean

    fun messageInChat(streamerUsername: String, author: TwitchUser, message: String)
}