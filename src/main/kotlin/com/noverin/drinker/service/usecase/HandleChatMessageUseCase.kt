package com.noverin.drinker.service.usecase

import com.noverin.drinker.domain.DrinkerService
import com.noverin.drinker.domain.drunk
import com.noverin.drinker.infrastructure.util.getUsername
import com.noverin.drinker.infrastructure.util.unwrap
import com.noverin.drinker.infrastructure.util.withMachine
import com.noverin.drinker.service.repository.TwitchUserRepository
import com.noverin.drinker.service.usecase.model.ChatMessage
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class HandleChatMessageUseCase (
    @Lazy
    val drinkerService: DrinkerService,
    val twitchUserRepository: TwitchUserRepository
){

    private val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

    private val mentoringBotName = "MentoringBot"
    private val mentoringBotDrunkMessages = listOf("выпил смузи", "уже пил смузи сегодня")

    operator fun invoke(chatMessage: ChatMessage) {
        val displayName = chatMessage.displayName ?: return
        val message = chatMessage.message ?: return

        if (isFromMentoringBot(displayName) && isDrunkMessage(message)) {
            val username = message.getUsername()

            logger.info("$username successfully drunk")

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