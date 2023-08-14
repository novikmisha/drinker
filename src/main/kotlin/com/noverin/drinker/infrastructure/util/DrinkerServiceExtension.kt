package com.noverin.drinker.infrastructure.util

import com.noverin.drinker.domain.Drinker
import com.noverin.drinker.domain.DrinkerService

fun DrinkerService.withMachine(twitchUserId: String, apply: (drinker: Drinker) -> Unit) =
    acquireStateMachine(twitchUserId)?.also {
        apply(it)
    }



