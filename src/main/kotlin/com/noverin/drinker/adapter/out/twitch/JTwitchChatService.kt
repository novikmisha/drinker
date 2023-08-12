package com.noverin.drinker.adapter.out.twitch

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.chat.TwitchChatBuilder
import com.noverin.drinker.domain.TwitchUser
import com.noverin.drinker.service.twitch.TwitchChatService
import org.springframework.stereotype.Service

@Service
class JTwitchChatService : TwitchChatService {

    override fun messageInChat(streamerUsername: String, author: TwitchUser, message: String) {
        val credentials = OAuth2Credential("twitch", author.token)

        TwitchChatBuilder.builder()
            .withChatAccount(credentials)
            .build()
            .use { chat ->
                chat.sendMessage(streamerUsername, message)
            }

    }
}