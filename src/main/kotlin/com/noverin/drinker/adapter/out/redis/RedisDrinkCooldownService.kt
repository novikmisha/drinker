package com.noverin.drinker.adapter.out.redis

import com.noverin.drinker.infrastructure.util.secondFromNow
import com.noverin.drinker.service.cooldown.DrinkCooldownService
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.concurrent.TimeUnit

@Service
class RedisDrinkCooldownService(
    val redisTemplate: RedisTemplate<Any, Any>
) : DrinkCooldownService {

    private val cooldownPattern = "cooldown:"

    override fun hasCooldown(username: String) =
        redisTemplate.hasKey(cooldownPattern + username)

    override fun putCooldown(username: String, until: OffsetDateTime) {
        val ttl = until.secondFromNow()
        redisTemplate.opsForValue().set(cooldownPattern + username, true, ttl, TimeUnit.SECONDS)
    }
}


