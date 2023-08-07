package com.noverin.drinker.infrastructure.util

fun String.getUsername(): String =
    split(" ").first().drop(1)
