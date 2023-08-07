package com.noverin.drinker.infrastructure.util

import com.noverin.drinker.domain.Drinker
import com.noverin.drinker.domain.DrinkerContext

fun DrinkerContext.drinker(): Drinker = stateMachine