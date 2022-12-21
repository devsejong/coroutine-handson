package net.chandol.sample.coroutine.api.client

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Duration


@Component
class SampleRestClient {
    private val client: WebClient = WebClient.create("https://httpbin.org")
    fun sampleGet(param: String): Mono<String> {
        return client.get()
            .uri { uriBuilder ->
                uriBuilder.path("/get")
                    .queryParam("param", param)
                    .build()
            }
            .exchangeToMono {
                it.bodyToMono(String::class.java)
            }
    }
}
