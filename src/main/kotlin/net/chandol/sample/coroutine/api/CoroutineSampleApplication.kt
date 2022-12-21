package net.chandol.sample.coroutine.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
class CoroutineSampleApplication

fun main(args: Array<String>) {
    runApplication<CoroutineSampleApplication>(*args)
}
