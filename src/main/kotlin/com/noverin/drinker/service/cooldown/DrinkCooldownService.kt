package com.noverin.drinker.service.cooldown

import java.time.OffsetDateTime

interface DrinkCooldownService {

    fun hasCooldown(username: String): Boolean

    fun putCooldown(username: String, until: OffsetDateTime)
}