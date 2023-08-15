package com.noverin.drinker.service.usecase.model

import java.time.Instant

class EnableBotCommand(
    val userId: String,
    val accessToken: String,
    val ttl: Instant,
    val refreshToken: String,
)