package com.noverin.drinker.service.action

import com.noverin.drinker.domain.DrinkerEvent
import com.noverin.drinker.domain.DrinkerState
import com.noverin.drinker.infrastructure.util.drinker
import com.noverin.drinker.service.cooldown.DrinkCooldownService
import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneOffset

@Component
class PutCooldownAction(
    val drinkerCooldownService: DrinkCooldownService
): AbstractAction() {

    override fun canExecuteOnState(drinkerState: DrinkerState) = false

    override fun execute(context: StateContext<DrinkerState, DrinkerEvent>) {
        val username = context.drinker().id

        // there can be bug if message was send at the end of the day
        // and parsed at start of the new day
        val until = LocalDate.now(ZoneOffset.UTC)
            .plusDays(1)
            // add this because mentoring server in not in UTC timezone monkaS
            .atStartOfDay(ZoneOffset.UTC)
            .plusHours(1)
            .toOffsetDateTime()

        drinkerCooldownService.putCooldown(username, until)
    }
}