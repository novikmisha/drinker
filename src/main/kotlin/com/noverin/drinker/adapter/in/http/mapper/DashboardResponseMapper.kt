package com.noverin.drinker.adapter.`in`.http.mapper

import com.noverin.drinker.adapter.`in`.http.model.DashboardResponse
import com.noverin.drinker.service.usecase.model.UserDashboard
import java.time.ZoneOffset

fun UserDashboard.toResponse() =
    DashboardResponse(username, botEnabled, totalBotDrink, lastBotDrinkDate?.atOffset(ZoneOffset.UTC)?.toLocalDate())