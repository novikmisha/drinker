package com.noverin.drinker.adapter.`in`.twitch

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import com.noverin.drinker.domain.DrinkerService
import com.noverin.drinker.domain.drunk
import com.noverin.drinker.infrastructure.util.getUsername
import com.noverin.drinker.infrastructure.util.unwrap
import com.noverin.drinker.infrastructure.util.withMachine
import com.noverin.drinker.service.repository.TwitchUserRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class ChatHandler(
    @Lazy
    val drinkerService: DrinkerService,
    val twitchUserRepository: TwitchUserRepository
) {

    private val mentoringBotName = "MentoringBot"
    private val mentoringBotDrunkMessages = listOf("выпил смузи", "уже пил смузи сегодня")

    fun onEvent(event: ChannelMessageEvent) {
        val messageEvent = event.messageEvent

        val displayName = messageEvent.userDisplayName.unwrap() ?: return
        val message = messageEvent.message.unwrap() ?: return

        if (isFromMentoringBot(displayName) && isDrunkMessage(message)) {
            val username = message.getUsername()
            if (twitchUserRepository.exists(username)) {
                drinkerService.withMachine(username) {
                    it.drunk()
                }
            }
        }
    }

    private fun isFromMentoringBot(displayName: String) =
        mentoringBotName.equals(displayName, true)

    private fun isDrunkMessage(message: String) =
        mentoringBotDrunkMessages.any { message.endsWith(it) }
}
