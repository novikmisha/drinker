package com.noverin.drinker.service.repository

import com.noverin.drinker.domain.TwitchUser

interface TwitchUserRepository {

    fun findAll() : List<TwitchUser>

    fun exists(username: String): Boolean

    fun findByUsername(username: String): TwitchUser
}