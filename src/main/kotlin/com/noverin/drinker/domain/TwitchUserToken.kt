package com.noverin.drinker.domain

import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "twitch_user_token")
class TwitchUserToken(
    @MapsId
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "twitch_user_id")
    val twitchUser: TwitchUser,
    @Column(name = "access_token")
    var accessToken: String,

    var ttl: Instant,

    @Column(name = "refresh_token")
    val refreshToken: String
) {
    @Id
    @Column(name = "twitch_user_id")
    var twitchUserId: String? = null
}