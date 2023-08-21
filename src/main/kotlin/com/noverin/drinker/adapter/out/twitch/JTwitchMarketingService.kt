package com.noverin.drinker.adapter.out.twitch

import com.github.twitch4j.chat.TwitchChat
import com.noverin.drinker.infrastructure.properties.TwitchProperties
import com.noverin.drinker.service.twitch.TwitchMarketingService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class JTwitchMarketingService(
    @Qualifier("marketingChat") @Lazy val twitchChat: TwitchChat,
    val twitchProperties: TwitchProperties
) : TwitchMarketingService {

    override fun promoteBotToUser(username: String)  {
        if (userIsLuckyOne(username)) {
            twitchChat.sendMessage(
                twitchProperties.streamerUsername,
                "@$username посмотри на https://drinker.bar и не пей смузи сам!"
            )
        }
    }

    private fun userIsLuckyOne(username: String) =
        0 == (0..9).random()
}