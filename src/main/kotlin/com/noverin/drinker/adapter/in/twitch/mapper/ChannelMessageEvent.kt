package com.noverin.drinker.adapter.`in`.twitch.mapper

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import com.noverin.drinker.infrastructure.util.unwrap
import com.noverin.drinker.service.usecase.model.ChatMessage

fun ChannelMessageEvent.toMessage() =
    ChatMessage(
        messageEvent.userDisplayName.unwrap(),
        messageEvent.message.unwrap()
    )