package com.noverin.drinker.infrastructure.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.noverin.drinker.adapter.out.jpa"])
@EntityScan(basePackages = ["com.noverin.drinker.domain"])
class JpaConfiguration