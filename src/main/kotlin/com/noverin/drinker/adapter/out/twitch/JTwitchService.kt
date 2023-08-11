package com.noverin.drinker.adapter.out.twitch

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.chat.TwitchChatBuilder
import com.github.twitch4j.helix.TwitchHelix
import com.noverin.drinker.domain.TwitchUser
import com.noverin.drinker.service.twitch.TwitchService
import org.springframework.stereotype.Service

@Service
class JTwitchService(
    val twitchHelix: TwitchHelix,
) : TwitchService{
    override fun isStreamOnline(username: String): Boolean =
        twitchHelix.getStreams(
            null,
            null,
            null,
            1,
            null,
            null,
            null,
            listOf(username)
        ).execute().streams.isNotEmpty()

    override fun messageInChat(streamerUsername: String, author: TwitchUser, message: String) {
        val credentials = OAuth2Credential("twitch", author.token)

        TwitchChatBuilder.builder()
            .withChatAccount(credentials)
            .build()
            .use {  chat ->
                chat.sendMessage(streamerUsername, message)
            }

    }
}