package com.noverin.drinker.service.action

import com.noverin.drinker.domain.DrinkerEvent
import com.noverin.drinker.domain.DrinkerState
import org.springframework.statemachine.action.Action

abstract class AbstractAction : Action<DrinkerState, DrinkerEvent> {
    open fun canExecuteOnState(drinkerState: DrinkerState) = false
}