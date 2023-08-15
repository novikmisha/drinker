package com.noverin.drinker.service.usecase

import com.noverin.drinker.domain.TwitchUser
import com.noverin.drinker.infrastructure.util.isBotEnabled
import com.noverin.drinker.service.repository.TwitchUserDrinkRepository
import com.noverin.drinker.service.repository.TwitchUserRepository
import com.noverin.drinker.service.usecase.model.GetDashboardCommand
import com.noverin.drinker.service.usecase.model.UserDashboard
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetDashboardUseCase(
    val twitchUserRepository: TwitchUserRepository,
    val twitchUserDrinkRepository: TwitchUserDrinkRepository
) {
    @Transactional
    operator fun invoke(command: GetDashboardCommand): UserDashboard {
        val user = twitchUserRepository.findForUpdate(command.userId)
            ?.also { user ->
                if (user.username != command.displayUsername) {
                    user.username = command.displayUsername
                    twitchUserRepository.save(user)
                }
            } ?: createTwitchUser(command)

        val count = twitchUserDrinkRepository.count(user.twitchId)
        val lastDrinkDate = twitchUserDrinkRepository.lastDrinkDate(user.twitchId)
        return UserDashboard(user.username, user.isBotEnabled(), count, lastDrinkDate)
    }

    private fun createTwitchUser(command: GetDashboardCommand): TwitchUser {
        val user = TwitchUser(
            command.userId,
            command.displayUsername
        )
        return twitchUserRepository.save(user)
    }
}