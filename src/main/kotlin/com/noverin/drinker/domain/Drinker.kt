package com.noverin.drinker.domain

import org.springframework.messaging.support.MessageBuilder
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.data.jpa.JpaStateMachineRepository
import org.springframework.statemachine.service.StateMachineService


enum class DrinkerState {
    WAITING, DRINKING
}

enum class DrinkerEvent {
    TIME_TO_DRINK, DRUNK
}

typealias Drinker = StateMachine<DrinkerState, DrinkerEvent>

typealias DrinkerService = StateMachineService<DrinkerState, DrinkerEvent>

typealias DrinkerRepository = JpaStateMachineRepository

typealias DrinkerContext = StateContext<DrinkerState, DrinkerEvent>

fun Drinker.drink() {
    sendEvent(
        MessageBuilder.withPayload(DrinkerEvent.TIME_TO_DRINK)
            .build()
    )
}

fun Drinker.drunk() {
    sendEvent(
        MessageBuilder.withPayload(DrinkerEvent.DRUNK)
            .build())
}
