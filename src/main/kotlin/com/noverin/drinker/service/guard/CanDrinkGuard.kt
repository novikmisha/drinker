package com.noverin.drinker.service.guard

import com.noverin.drinker.domain.DrinkerContext
import com.noverin.drinker.domain.DrinkerEvent
import com.noverin.drinker.domain.DrinkerState
import com.noverin.drinker.infrastructure.properties.TwitchProperties
import com.noverin.drinker.infrastructure.util.drinker
import com.noverin.drinker.service.cooldown.DrinkCooldownService
import com.noverin.drinker.service.twitch.TwitchService
import org.springframework.statemachine.guard.Guard
import org.springframework.stereotype.Component

@Component
class CanDrinkGuard(
    val drinkCooldownService: DrinkCooldownService,
    val twitchService: TwitchService,
    val twitchProperties: TwitchProperties
) : Guard<DrinkerState, DrinkerEvent> {

    override fun evaluate(context: DrinkerContext): Boolean {
        val username = context.drinker().id
//        return !drinkCooldownService.hasCooldown(username)
//            && twitchService.isStreamOnline(twitchProperties.streamerUsername)
        return true
    }
}