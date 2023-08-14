package com.noverin.drinker.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http.authorizeRequests()
            .antMatchers("/login")
            .permitAll()
            .antMatchers("/css/style.css")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .loginPage("/login")
            .and()
            .logout()
            .and()
            .build()
}