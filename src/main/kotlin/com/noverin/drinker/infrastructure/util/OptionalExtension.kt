package com.noverin.drinker.infrastructure.util

import java.util.*

fun <T> Optional<T>.unwrap(): T? = orElse(null)
