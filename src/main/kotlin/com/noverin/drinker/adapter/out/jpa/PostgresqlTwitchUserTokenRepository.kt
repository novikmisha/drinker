package com.noverin.drinker.adapter.out.jpa

import com.noverin.drinker.domain.TwitchUserToken
import com.noverin.drinker.service.repository.TwitchUserTokenRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.LockModeType

interface JpaTwitchTokenUserRepository: JpaRepository<TwitchUserToken, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select tokens from TwitchUserToken tokens where tokens.twitchUserId = ?1")
    fun findForUpdate(twitchUserId: String): TwitchUserToken?
}

@Repository
class PostgresqlTwitchUserTokenRepository(
    val jpaTwitchTokenUserRepository: JpaTwitchTokenUserRepository
): TwitchUserTokenRepository {

    override fun findByTwitchUserId(twitchUserId: String) =
        jpaTwitchTokenUserRepository.findByIdOrNull(twitchUserId)

    override fun findForUpdate(twitchUserId: String) =
        jpaTwitchTokenUserRepository.findForUpdate(twitchUserId)

    override fun save(twitchUserToken: TwitchUserToken) =
        jpaTwitchTokenUserRepository.save(twitchUserToken)

    override fun deleteByTwitchUserId(twitchUserId: String) =
        jpaTwitchTokenUserRepository.deleteById(twitchUserId)

    override fun findAll(): List<TwitchUserToken> =
        jpaTwitchTokenUserRepository.findAll()
}