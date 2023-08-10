package com.noverin.drinker.service.usecase

import com.noverin.drinker.domain.DrinkerService
import com.noverin.drinker.domain.drink
import com.noverin.drinker.infrastructure.util.withMachine
import com.noverin.drinker.service.repository.TwitchUserRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class StartDrinkUseCase(
    @Lazy
    val drinkerService: DrinkerService,
    val twitchUserRepository: TwitchUserRepository
) {
    operator fun invoke() {
        twitchUserRepository.findAll().forEach { user ->
            drinkerService.withMachine(user.username) {
                it.drink()
            }
        }
    }
}