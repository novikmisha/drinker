package com.noverin.drinker.infrastructure.util

import com.noverin.drinker.domain.Drinker
import com.noverin.drinker.domain.DrinkerService

fun DrinkerService.withMachine(userId: String, apply: (drinker: Drinker) -> Unit) =
    acquireStateMachine(userId)?.also {
        apply(it)
    }



