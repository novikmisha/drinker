package com.noverin.drinker.infrastructure.util

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

fun OffsetDateTime.secondFromNow(): Long {
    val now = LocalDateTime.now().atOffset(ZoneOffset.UTC)
    return ChronoUnit.SECONDS.between(now, this)
}