package com.noverin.drinker.adapter.out.twitch

import com.github.twitch4j.helix.TwitchHelix
import com.noverin.drinker.service.twitch.TwitchService
import org.springframework.stereotype.Service

@Service
class JTwitchService(
    val twitchHelix: TwitchHelix,
) : TwitchService {

    override fun isStreamOnline(username: String): Boolean =
        twitchHelix.getStreams(
            null,
            null,
            null,
            1,
            null,
            null,
            null,
            listOf(username)
        ).execute().streams.isNotEmpty()
}