package com.noverin.drinker.adapter.out.jpa

import com.noverin.drinker.domain.TwitchUserToken
import com.noverin.drinker.service.repository.TwitchUserTokenRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.LockModeType

interface JpaTwitchTokenUserRepository : JpaRepository<TwitchUserToken, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select tokens from TwitchUserToken tokens where tokens.userId = ?1")
    fun findForUpdate(userId: String): TwitchUserToken?
}

@Repository
class PostgresqlTwitchUserTokenRepository(
    val jpaTwitchTokenUserRepository: JpaTwitchTokenUserRepository
) : TwitchUserTokenRepository {

    override fun findByUserId(userId: String) =
        jpaTwitchTokenUserRepository.findByIdOrNull(userId)

    override fun findForUpdate(userId: String) =
        jpaTwitchTokenUserRepository.findForUpdate(userId)

    override fun save(twitchUserToken: TwitchUserToken) =
        jpaTwitchTokenUserRepository.save(twitchUserToken)

    override fun deleteByUserId(userId: String) =
        jpaTwitchTokenUserRepository.deleteById(userId)

    override fun findAll(): List<TwitchUserToken> =
        jpaTwitchTokenUserRepository.findAll()
}