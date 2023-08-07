package com.noverin.drinker.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("twitch")
@ConstructorBinding
class TwitchProperties(
    val streamerUsername: String,

    val clientId: String,
    val clientSecret: String
)