package com.noverin.drinker.service.usecase

import com.noverin.drinker.domain.DrinkerService
import com.noverin.drinker.domain.drink
import com.noverin.drinker.infrastructure.util.withMachine
import com.noverin.drinker.service.repository.TwitchUserTokenRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class StartDrinkUseCase(
    @Lazy
    val drinkerService: DrinkerService,
    val twitchUserTokenRepository: TwitchUserTokenRepository
) {
    operator fun invoke() {
        twitchUserTokenRepository.findAll().forEach { token ->
            drinkerService.withMachine(token.userId!!) {
                it.drink()
            }
        }
    }
}