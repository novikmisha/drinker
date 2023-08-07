package com.noverin.drinker.infrastructure.config

import com.noverin.drinker.infrastructure.properties.TwitchProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    value = [
        TwitchProperties::class,
    ]
)
class PropertiesConfiguration