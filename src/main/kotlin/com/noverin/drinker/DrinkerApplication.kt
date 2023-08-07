package com.noverin.drinker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DrinkerApplication

fun main(args: Array<String>) {
    runApplication<DrinkerApplication>(*args)
}
