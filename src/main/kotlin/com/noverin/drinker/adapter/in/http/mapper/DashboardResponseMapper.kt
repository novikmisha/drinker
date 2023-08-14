package com.noverin.drinker.adapter.`in`.http.mapper

import com.noverin.drinker.adapter.`in`.http.model.DashboardResponse
import com.noverin.drinker.service.usecase.model.UserDashboard

fun UserDashboard.toResponse() =
    DashboardResponse(username, botEnabled, 0, null)