package com.noverin.drinker.adapter.`in`.scheduler

import com.noverin.drinker.service.usecase.StartDrinkUseCase
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class DrinkScheduler(
    val startDrinkUseCase: StartDrinkUseCase
) {

    private val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.MINUTES)
    fun drink() {
        logger.info("starting drinking from scheduler")
        startDrinkUseCase()
    }
}

