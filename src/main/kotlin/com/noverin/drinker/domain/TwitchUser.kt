package com.noverin.drinker.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "twitch_user")
class TwitchUser(
    @Id
    @Column(name = "twitch_id")
    val twitchId: String,
    var username: String,
) {
    @OneToOne(mappedBy = "twitchUser")
    var tokens: TwitchUserToken? = null
}