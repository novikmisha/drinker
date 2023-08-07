package com.noverin.drinker

import com.noverin.drinker.service.cooldown.DrinkCooldownService
import com.noverin.drinker.service.twitch.TwitchService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.OffsetDateTime

@SpringBootTest
class DrinkerApplicationTests(
    @Autowired val drinkCooldownService: DrinkCooldownService
) {

    @Test
    fun contextLoads() {
        drinkCooldownService.putCooldown("testing", OffsetDateTime.MAX)
        assert(drinkCooldownService.hasCooldown("testing"))
    }

}
