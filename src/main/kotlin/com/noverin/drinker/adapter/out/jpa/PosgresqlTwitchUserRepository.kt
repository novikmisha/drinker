package com.noverin.drinker.adapter.out.jpa

import com.noverin.drinker.domain.TwitchUser
import com.noverin.drinker.infrastructure.util.unwrap
import com.noverin.drinker.service.repository.TwitchUserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import javax.persistence.LockModeType

interface JpaTwitchUserRepository : JpaRepository<TwitchUser, String> {

    fun findByUsername(username: String): TwitchUser?

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select user from TwitchUser user where user.twitchId = ?1")
    fun findForUpdate(id: String): TwitchUser?
}

@Repository
class PosgresqlTwitchUserRepository(
    val jpaTwitchUserRepository: JpaTwitchUserRepository
) : TwitchUserRepository {

    override fun findAll(): List<TwitchUser> =
        jpaTwitchUserRepository.findAll()

    override fun findById(id: String) =
        jpaTwitchUserRepository.findByIdOrNull(id)

    override fun findForUpdate(id: String) =
        jpaTwitchUserRepository.findForUpdate(id)

    override fun findByUsername(username: String): TwitchUser? =
        jpaTwitchUserRepository.findByUsername(username)

    override fun save(twitchUser: TwitchUser) =
        jpaTwitchUserRepository.save(twitchUser)
}