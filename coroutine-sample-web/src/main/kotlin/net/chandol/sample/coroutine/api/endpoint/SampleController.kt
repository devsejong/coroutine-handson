package net.chandol.sample.coroutine.api.endpoint

import kotlinx.coroutines.delay
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.UUID
import kotlin.random.Random


// request -> netty(spring webflux -> coroutine) -> response
// https://docs.spring.io/spring-framework/docs/current/reference/html/languages.html#coroutines
// https://spring.io/blog/2019/04/12/going-reactive-with-spring-Coroutines-and-kotlin-flow
@RestController
class SampleController {
    @GetMapping("/hello")
    suspend fun helloWorld(): Map<String, LocalDateTime> {
        val caller = UUID.randomUUID()
        log.info { "$caller 요청 받음" }

        val currentTime = currentTime()
        log.info { "$caller 요청 완료!! $currentTime" }

        return mapOf("hello" to currentTime)
    }

    fun currentTime(): LocalDateTime {
        return LocalDateTime.now()
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
