package com.noverin.drinker.service.repository

import com.noverin.drinker.domain.TwitchUserDrink
import java.time.Instant

interface TwitchUserDrinkRepository {

    fun save(drink: TwitchUserDrink): TwitchUserDrink

    fun count(userId: String): Long

    fun lastDrinkDate(userId: String): Instant?
}