package com.noverin.drinker.infrastructure.util

import com.noverin.drinker.domain.TwitchUser

fun TwitchUser.isBotEnabled() = tokens != null