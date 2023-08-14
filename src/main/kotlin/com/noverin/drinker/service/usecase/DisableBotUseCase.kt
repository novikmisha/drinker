package com.noverin.drinker.service.usecase

import com.noverin.drinker.domain.DrinkerRepository
import com.noverin.drinker.domain.DrinkerService
import com.noverin.drinker.service.repository.TwitchUserRepository
import com.noverin.drinker.service.repository.TwitchUserTokenRepository
import com.noverin.drinker.service.usecase.model.DisableBotCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DisableBotUseCase(
    val twitchUserRepository: TwitchUserRepository,
    val twitchUserTokenRepository: TwitchUserTokenRepository,
    val drinkerService: DrinkerService,
    val drinkerRepository: DrinkerRepository
) {

    @Transactional
    operator fun invoke(command: DisableBotCommand) {
        val user = twitchUserRepository.findById(command.twitchUserId)
            ?: error("user ${command.twitchUserId} not found")

        drinkerService.releaseStateMachine(user.twitchId)

        if (drinkerRepository.existsById(user.twitchId)) {
            drinkerRepository.deleteById(user.twitchId)
        }

        twitchUserTokenRepository.deleteByTwitchUserId(user.twitchId)
    }
}