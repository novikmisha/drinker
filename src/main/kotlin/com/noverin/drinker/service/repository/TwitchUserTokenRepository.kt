package com.noverin.drinker.service.repository

import com.noverin.drinker.domain.TwitchUserToken

interface TwitchUserTokenRepository {

    fun findByUserId(userId: String): TwitchUserToken?

    fun save(twitchUserToken: TwitchUserToken): TwitchUserToken

    fun findForUpdate(userId: String): TwitchUserToken?

    fun deleteByUserId(userId: String)

    fun findAll(): List<TwitchUserToken>
}