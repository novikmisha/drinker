package com.noverin.drinker.service.action

import com.noverin.drinker.domain.DrinkerContext
import com.noverin.drinker.domain.TwitchUser
import com.noverin.drinker.domain.TwitchUserDrink
import com.noverin.drinker.infrastructure.util.drinker
import com.noverin.drinker.service.repository.TwitchUserDrinkRepository
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class SaveTwitchUserDrinkAction(
    val twitchUserDrinkRepository: TwitchUserDrinkRepository
) : AbstractAction() {

    override fun execute(context: DrinkerContext) {
        val userId = context.drinker().id
        val drink = TwitchUserDrink(Instant.now(), TwitchUser(userId, ""))
        twitchUserDrinkRepository.save(drink)
    }
}