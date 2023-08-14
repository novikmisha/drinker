package com.noverin.drinker.adapter.out.twitch

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.chat.TwitchChatBuilder
import com.noverin.drinker.service.twitch.TwitchChatService
import org.springframework.stereotype.Service

@Service
class JTwitchChatService : TwitchChatService {

    override fun messageInChat(streamerUsername: String, accessToken: String, message: String) {
        val credentials = OAuth2Credential("twitch", accessToken)

        TwitchChatBuilder.builder()
            .withChatAccount(credentials)
            .build()
            .use { chat ->
                chat.sendMessage(streamerUsername, message)
                // todo do something with this
                // i have to add some await till send message task will taken by taskExecutor
                // and with that twitchChat.close() will wait till all tasks will be done
                // and close only after that
                Thread.sleep(700L)
            }
    }
}