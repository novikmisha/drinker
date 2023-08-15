package com.noverin.drinker.service.twitch

import com.noverin.drinker.domain.TwitchUserToken

interface TwitchUserTokenService {

    fun withUserTokens(userId: String, apply: (tokens: TwitchUserToken) -> Unit)
}