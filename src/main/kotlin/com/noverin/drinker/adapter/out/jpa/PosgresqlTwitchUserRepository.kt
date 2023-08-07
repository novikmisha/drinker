package com.noverin.drinker.adapter.out.jpa

import com.noverin.drinker.domain.TwitchUser
import com.noverin.drinker.infrastructure.util.unwrap
import com.noverin.drinker.service.repository.TwitchUserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface JpaTwitchUserRepository : JpaRepository<TwitchUser, String>

@Repository
class PosgresqlTwitchUserRepository(
    val jpaTwitchUserRepository: JpaTwitchUserRepository
) : TwitchUserRepository {

    override fun findAll(): List<TwitchUser> =
        jpaTwitchUserRepository.findAll()

    override fun exists(username: String) =
        jpaTwitchUserRepository.existsById(username)

    override fun findByUsername(username: String): TwitchUser =
        jpaTwitchUserRepository.findById(username).unwrap() ?: error("user $username not found")
}