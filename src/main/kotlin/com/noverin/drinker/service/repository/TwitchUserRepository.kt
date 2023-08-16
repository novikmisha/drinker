package com.noverin.drinker.service.repository

import com.noverin.drinker.domain.TwitchUser

interface TwitchUserRepository {

    fun findAll(): List<TwitchUser>

    fun findById(id: String): TwitchUser?

    fun findForUpdate(id: String): TwitchUser?

    fun findByUsernameIgnoreCase(username: String): TwitchUser?

    fun save(twitchUser: TwitchUser): TwitchUser
}