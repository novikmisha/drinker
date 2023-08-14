package com.noverin.drinker.adapter.`in`.http.model

import java.time.LocalDate

class DashboardResponse(
    val username: String,
    val botEnabled: Boolean,
    val totalBotDrink: Int,
    val lastBotDrinkDate: LocalDate?
)