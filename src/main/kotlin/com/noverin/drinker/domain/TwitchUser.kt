package com.noverin.drinker.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "twitch_user")
class TwitchUser(
    @Id
    val username: String,
    val token: String
)