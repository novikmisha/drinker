package com.noverin.drinker.infrastructure.config

import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import com.github.twitch4j.chat.TwitchChat
import com.github.twitch4j.chat.TwitchChatBuilder
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import com.github.twitch4j.events.ChannelGoLiveEvent
import com.github.twitch4j.helix.TwitchHelix
import com.noverin.drinker.adapter.`in`.twitch.ChatHandler
import com.noverin.drinker.adapter.`in`.twitch.StreamGoLiveHandler
import com.noverin.drinker.infrastructure.properties.TwitchProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TwitchConfiguration(
    val twitchProperties: TwitchProperties,
    val chatHandler: ChatHandler,
    val streamGoLiveHandler: StreamGoLiveHandler
) {

    @Bean
    fun twitchClient(): TwitchClient =
        TwitchClientBuilder.builder()
            .withClientId(twitchProperties.clientId)
            .withClientSecret(twitchProperties.clientSecret)
            .withEnableHelix(true)
            .build().also { client ->
                client.clientHelper.enableStreamEventListener(twitchProperties.streamerUsername);
                client.eventManager.onEvent(ChannelGoLiveEvent::class.java, streamGoLiveHandler::onEvent)
            }

    @Bean
    fun helix(): TwitchHelix =
        twitchClient().helix


    @Bean
    fun chat(): TwitchChat {
        return TwitchChatBuilder.builder()
            .build().also { chat ->
                chat.eventManager.onEvent(ChannelMessageEvent::class.java, chatHandler::onEvent)
                chat.joinChannel(twitchProperties.streamerUsername)
            }
    }

}