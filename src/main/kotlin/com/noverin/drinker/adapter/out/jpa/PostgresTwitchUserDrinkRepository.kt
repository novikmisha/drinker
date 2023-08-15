package com.noverin.drinker.adapter.out.jpa

import com.noverin.drinker.domain.TwitchUserDrink
import com.noverin.drinker.service.repository.TwitchUserDrinkRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant

interface JpaTwitchUserDrinkRepository : JpaRepository<TwitchUserDrink, String> {

    @Query("select count(drink) from TwitchUserDrink drink where drink.twitchUser.twitchId = ?1")
    fun countAllByUser(userId: String): Long

    @Query("select max(drink.time) from TwitchUserDrink drink where drink.twitchUser.twitchId = ?1")
    fun lastDrinkDateByUser(userId: String): Instant?
}

@Repository
class PostgresTwitchUserDrinkRepository(
    val jpaTwitchUserDrinkRepository: JpaTwitchUserDrinkRepository
) : TwitchUserDrinkRepository {
    override fun save(drink: TwitchUserDrink) =
        jpaTwitchUserDrinkRepository.save(drink)

    override fun count(userId: String) =
        jpaTwitchUserDrinkRepository.countAllByUser(userId)

    override fun lastDrinkDate(userId: String) =
        jpaTwitchUserDrinkRepository.lastDrinkDateByUser(userId)
}