package com.noverin.drinker.service.usecase

import com.noverin.drinker.domain.DrinkerService
import com.noverin.drinker.domain.drunk
import com.noverin.drinker.infrastructure.properties.TwitchProperties
import com.noverin.drinker.infrastructure.util.getUsername
import com.noverin.drinker.infrastructure.util.withMachine
import com.noverin.drinker.service.repository.TwitchUserRepository
import com.noverin.drinker.service.twitch.TwitchMarketingService
import com.noverin.drinker.service.usecase.model.ChatMessage
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class HandleChatMessageUseCase(
    @Lazy
    val drinkerService: DrinkerService,
    val twitchUserRepository: TwitchUserRepository,
    val twitchMarketingService: TwitchMarketingService,
    twitchProperties: TwitchProperties
) {

    private val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

    private val mentoringNames = setOf("MentoringBot", twitchProperties.adminUsername)
    private val mentoringBotDrunkMessages = listOf("выпил смузи", "уже пил смузи сегодня")

    operator fun invoke(chatMessage: ChatMessage) {
        val displayName = chatMessage.displayName ?: return
        val message = chatMessage.message ?: return

        if (isFromMentoringBot(displayName) && isDrunkMessage(message)) {
            // todo what if user change name?
            // do i want to make request for username by id before drink command?
            val username = message.getUsername()

            logger.info("$username successfully drunk")

            twitchUserRepository.findByUsernameIgnoreCase(username)?.let { user ->
                drinkerService.withMachine(user.twitchId) {
                    it.drunk()
                }
            } ?: twitchMarketingService.promoteBotToUser(username)
        }
    }

    private fun isFromMentoringBot(displayName: String) =
        mentoringNames.any { it.equals(displayName, true) }

    private fun isDrunkMessage(message: String) =
        mentoringBotDrunkMessages.any { message.endsWith(it) }
}