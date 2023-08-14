package com.noverin.drinker.service.usecase

import com.noverin.drinker.domain.TwitchUserToken
import com.noverin.drinker.service.repository.TwitchUserRepository
import com.noverin.drinker.service.repository.TwitchUserTokenRepository
import com.noverin.drinker.service.usecase.model.EnableBotCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EnableBotUseCase(
    val twitchUserRepository: TwitchUserRepository,
    val twitchUserTokenRepository: TwitchUserTokenRepository
) {

    @Transactional
    operator fun invoke(command: EnableBotCommand) {
        val user = twitchUserRepository.findById(command.twitchUserId)
            ?: error("user ${command.twitchUserId} not found")

        val tokens = TwitchUserToken(user, command.accessToken, command.ttl, command.refreshToken)
        twitchUserTokenRepository.save(tokens)
    }
}