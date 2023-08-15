package com.noverin.drinker.service.usecase.model

import java.time.Instant

class UserDashboard(
    val username: String,
    val botEnabled: Boolean,
    val totalBotDrink: Long,
    val lastBotDrinkDate: Instant?
)