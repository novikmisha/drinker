package com.noverin.drinker.domain

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "twitch_user_drink")
class TwitchUserDrink(
    val time: Instant,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "twitch_user_id")
    val twitchUser: TwitchUser
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}