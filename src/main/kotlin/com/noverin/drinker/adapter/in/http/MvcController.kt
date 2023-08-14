package com.noverin.drinker.adapter.`in`.http

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MvcController {

    @GetMapping("/login")
    fun login() = "login.html"

    @GetMapping("/")
    fun dashboard() = "index.html"
}