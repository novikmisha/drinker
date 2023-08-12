package com.noverin.drinker.service.twitch

import com.noverin.drinker.domain.TwitchUser

interface TwitchChatService {

    fun messageInChat(streamerUsername: String, author: TwitchUser, message: String)
}