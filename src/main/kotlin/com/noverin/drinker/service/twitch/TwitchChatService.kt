package com.noverin.drinker.service.twitch

interface TwitchChatService {

    fun messageInChat(streamerUsername: String, accessToken: String, message: String)
}