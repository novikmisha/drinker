package com.noverin.drinker.adapter.`in`.twitch

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import com.noverin.drinker.adapter.`in`.twitch.mapper.toMessage
import com.noverin.drinker.service.usecase.HandleChatMessageUseCase
import org.springframework.stereotype.Component

@Component
class ChatHandler(
    val handleChatMessageUseCase: HandleChatMessageUseCase
) {

    fun onEvent(event: ChannelMessageEvent) {
        handleChatMessageUseCase.invoke(event.toMessage())
    }
}
