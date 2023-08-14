package com.noverin.drinker.service.twitch

import com.noverin.drinker.domain.TwitchUserToken

interface TwitchUserTokenService {

    fun withUserTokens(twitchUserId: String, apply: (tokens: TwitchUserToken) -> Unit)
}