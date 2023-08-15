package com.noverin.drinker.adapter.`in`.http

import com.noverin.drinker.adapter.`in`.http.mapper.getDashboardCommand
import com.noverin.drinker.adapter.`in`.http.mapper.toResponse
import com.noverin.drinker.service.usecase.GetDashboardUseCase
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class MvcController(
    val getDashboardUseCase: GetDashboardUseCase
) {

    @GetMapping("/login")
    fun login(principal: Principal?): String {
        principal?.let {
            return "redirect:/"
        }
        return "login"
    }

    @GetMapping("/")
    fun dashboard(principal: OAuth2AuthenticationToken, model: Model): String {
        val dashboardResponse = getDashboardUseCase(principal.getDashboardCommand()).toResponse()
        model.addAttribute("dashboard", dashboardResponse);
        return "index"
    }
}