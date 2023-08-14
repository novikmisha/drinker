package com.noverin.drinker.service.usecase

import com.noverin.drinker.domain.TwitchUser
import com.noverin.drinker.infrastructure.util.isBotEnabled
import com.noverin.drinker.service.repository.TwitchUserRepository
import com.noverin.drinker.service.usecase.model.GetDashboardCommand
import com.noverin.drinker.service.usecase.model.UserDashboard
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetDashboardUseCase(
    val twitchUserRepository: TwitchUserRepository
) {
    @Transactional
    operator fun invoke(command: GetDashboardCommand): UserDashboard {
        val user = twitchUserRepository.findForUpdate(command.twitchUserId)
            ?.also { user ->
                if (user.username != command.displayUsername) {
                    user.username = command.displayUsername
                    twitchUserRepository.save(user)
                }
            } ?: createTwitchUser(command)

        return UserDashboard(user.username, user.isBotEnabled())
    }

    private fun createTwitchUser(command: GetDashboardCommand): TwitchUser {
        val user = TwitchUser(
            command.twitchUserId,
            command.displayUsername
        )
        return twitchUserRepository.save(user)
    }
}