package com.noverin.drinker.adapter.`in`.http.mapper

import com.noverin.drinker.service.usecase.model.GetDashboardCommand
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken

fun OAuth2AuthenticationToken.getDashboardCommand(): GetDashboardCommand {
    val preferredUsername = principal.attributes["preferred_username"]?.toString() ?: error("no username in token")
    val twitchId = principal.attributes["sub"]?.toString() ?: error("no twitchId in token")

    return GetDashboardCommand(twitchId, preferredUsername)
}