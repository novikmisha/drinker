package com.noverin.drinker.infrastructure.statemachine

import com.noverin.drinker.domain.Drinker
import com.noverin.drinker.domain.DrinkerEvent
import com.noverin.drinker.domain.DrinkerState
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.statemachine.ObjectStateMachine
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.configurers.AbstractTransitionConfigurer
import org.springframework.statemachine.config.configurers.InternalTransitionConfigurer
import org.springframework.statemachine.listener.StateMachineListener
import org.springframework.statemachine.state.State
import org.springframework.statemachine.transition.Transition
import org.springframework.statemachine.trigger.TimerTrigger
import org.springframework.stereotype.Component

@Component
class DrinkerListener : StateMachineListener<DrinkerState, DrinkerEvent> {

    private val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

    override fun stateChanged(from: State<DrinkerState, DrinkerEvent>?, to: State<DrinkerState, DrinkerEvent>) {
    }

    override fun stateEntered(state: State<DrinkerState, DrinkerEvent>) {
        logger.info("state ${state.id} entered")
    }

    override fun stateExited(state: State<DrinkerState, DrinkerEvent>) {
    }

    override fun eventNotAccepted(event: Message<DrinkerEvent>) {
    }

    override fun transition(transition: Transition<DrinkerState, DrinkerEvent>) {
    }

    override fun stateMachineStarted(drinker: Drinker) {
        logger.info("start applying fix for drinker ${drinker.id}")

        if (drinker is ObjectStateMachine) {
            drinker.transitions.mapNotNull { t -> t.trigger }
                .filterIsInstance<TimerTrigger<*, *>>()
                .forEach { t -> t.arm() }
        }

        logger.info("fix applied drinker ${drinker.id}")
    }

    override fun stateMachineStopped(stateMachine: StateMachine<DrinkerState, DrinkerEvent>?) {
    }

    override fun stateMachineError(stateMachine: StateMachine<DrinkerState, DrinkerEvent>?, exception: Exception?) {
    }

    override fun transitionStarted(transition: Transition<DrinkerState, DrinkerEvent>?) {
    }

    override fun transitionEnded(transition: Transition<DrinkerState, DrinkerEvent>?) {
    }

    override fun extendedStateChanged(key: Any?, value: Any?) {
    }

    override fun stateContext(stateContext: StateContext<DrinkerState, DrinkerEvent>?) {
    }
}

fun <S, E> InternalTransitionConfigurer<S, E>.timer(period: Long, count: Int): InternalTransitionConfigurer<S, E> {
    timer(period)
    if (this is AbstractTransitionConfigurer<*, *>) {
        setCount(count)
    }
    return this
}