package com.noverin.drinker.infrastructure.util

import com.noverin.drinker.domain.Drinker
import com.noverin.drinker.domain.DrinkerService

fun DrinkerService.withMachine(username: String, apply: (drinker: Drinker) -> Unit) =
    acquireStateMachine(username)?.also {
        apply(it)
    }



