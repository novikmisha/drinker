package com.noverin.drinker.domain

import javax.persistence.*

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