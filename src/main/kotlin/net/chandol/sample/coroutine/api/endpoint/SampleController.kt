package net.chandol.sample.coroutine.api.endpoint

import mu.KotlinLogging
import net.chandol.sample.coroutine.api.client.SampleRestClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

// request -> netty(spring webflux -> coroutine) -> response
// https://docs.spring.io/spring-framework/docs/current/reference/html/languages.html#coroutines
// https://spring.io/blog/2019/04/12/going-reactive-with-spring-Coroutines-and-kotlin-flow
@RestController
class SampleController(
    val sampleRestClient: SampleRestClient
) {
    //webclient 호출 이후 결괏값 응답하는 coroutine handler
    @GetMapping("/hello")
    fun hello(@RequestParam param: String): Mono<ResponseEntity<String>> {
        return Mono.just(param)
            .doOnNext { log.info { "요청 받음... $it" } }
            .flatMap { sampleRestClient.sampleGet(param) }
            .map { ResponseEntity.ok(it) }
            .doOnNext { log.info { "요청 완료!... ${it.body}" } }
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
