package com.noverin.drinker.service.repository

import com.noverin.drinker.domain.TwitchUserToken

interface TwitchUserTokenRepository {

    fun findByTwitchUserId(twitchUserId: String): TwitchUserToken?

    fun save(twitchUserToken: TwitchUserToken): TwitchUserToken

    fun findForUpdate(twitchUserId: String): TwitchUserToken?

    fun deleteByTwitchUserId(twitchUserId: String)

    fun findAll(): List<TwitchUserToken>
}