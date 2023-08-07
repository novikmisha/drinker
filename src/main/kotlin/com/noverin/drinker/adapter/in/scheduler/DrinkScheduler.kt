package com.noverin.drinker.adapter.`in`.scheduler

import com.noverin.drinker.domain.DrinkerService
import com.noverin.drinker.domain.drink
import com.noverin.drinker.infrastructure.util.withMachine
import com.noverin.drinker.service.repository.TwitchUserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class DrinkScheduler(
    val drinkerService: DrinkerService,
    val twitchUserRepository: TwitchUserRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        drink()
    }

    @Scheduled(cron = "@hourly")
    fun drink() {
        twitchUserRepository.findAll().forEach { user ->
            drinkerService.withMachine(user.username) {
                it.drink()
            }
        }
    }
}

