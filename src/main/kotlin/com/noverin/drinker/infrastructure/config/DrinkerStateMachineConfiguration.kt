package com.noverin.drinker.infrastructure.config

import com.noverin.drinker.domain.DrinkerEvent
import com.noverin.drinker.domain.DrinkerState
import com.noverin.drinker.infrastructure.statemachine.DrinkerListener
import com.noverin.drinker.infrastructure.statemachine.timer
import com.noverin.drinker.service.action.AbstractAction
import com.noverin.drinker.service.action.DrinkingAction
import com.noverin.drinker.service.action.PutCooldownAction
import com.noverin.drinker.service.guard.CanDrinkGuard
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter
import org.springframework.statemachine.config.StateMachineFactory
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor
import org.springframework.statemachine.data.jpa.JpaStateMachineRepository
import org.springframework.statemachine.persist.StateMachineRuntimePersister
import org.springframework.statemachine.service.DefaultStateMachineService
import org.springframework.statemachine.service.StateMachineService
import java.util.*


@Configuration
@EnableStateMachineFactory
class DrinkerStateMachineConfiguration(
    val actions: List<AbstractAction>,
    val drinkingAction: DrinkingAction,
    val canDrunkGuard: CanDrinkGuard,
    val putCooldownAction: PutCooldownAction,
    val jpaStateMachineRepository: JpaStateMachineRepository,
    val drinkerListener: DrinkerListener
) : EnumStateMachineConfigurerAdapter<DrinkerState, DrinkerEvent>() {

    @Configuration
    class StateMachineServiceConfiguration {
        @Bean
        fun stateMachineService(
            stateMachineFactory: StateMachineFactory<DrinkerState, DrinkerEvent>,
            stateMachineRuntimePersister: StateMachineRuntimePersister<DrinkerState, DrinkerEvent, String>
        ): StateMachineService<DrinkerState, DrinkerEvent> {
            return DefaultStateMachineService(stateMachineFactory, stateMachineRuntimePersister)
        }
    }

    @Bean
    fun stateMachineRuntimePersister(
    ): StateMachineRuntimePersister<DrinkerState, DrinkerEvent, String> {
        return JpaPersistingStateMachineInterceptor(jpaStateMachineRepository)
    }

    override fun configure(states: StateMachineStateConfigurer<DrinkerState, DrinkerEvent>) {
        val initial = states.withStates().initial(DrinkerState.WAITING)

        DrinkerState.values().forEach { state ->
            actions.firstOrNull { action -> action.canExecuteOnState(state) }?.let { action ->
                initial.state(state, action)
            }
        }

        initial.states(EnumSet.allOf(DrinkerState::class.java))
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<DrinkerState, DrinkerEvent>) {
        //@formatter:off
        transitions
            .withExternal()
                .source(DrinkerState.WAITING)
                .target(DrinkerState.DRINKING)
                .event(DrinkerEvent.TIME_TO_DRINK)
                .guard(canDrunkGuard)
                .action(drinkingAction)
            .and()
            .withExternal()
                .source(DrinkerState.DRINKING)
                .target(DrinkerState.WAITING)
                .event(DrinkerEvent.DRUNK)
                .action(putCooldownAction)
            .and()
            .withInternal()
                .source(DrinkerState.DRINKING)
                .action(drinkingAction)
                .timer(10_000, Int.MAX_VALUE)
        //@formatter:on
    }

    override fun configure(config: StateMachineConfigurationConfigurer<DrinkerState, DrinkerEvent>) {
        //@formatter:off
        config
            .withPersistence()
                .runtimePersister(stateMachineRuntimePersister())
            .and()
            .withConfiguration()
                .listener(drinkerListener)
        //@formatter:on
    }
}


