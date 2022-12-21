package net.chandol.sample.coroutine.api.endpoint

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.take
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger


@OptIn(ObsoleteCoroutinesApi::class)
@RestController
class SampleStreamController {
    @GetMapping("/hello-stream")
    suspend fun helloStream(): Flow<Int> {
        val countDown = AtomicInteger(100)
        return ticker(1000).receiveAsFlow()
            .map { countDown.decrementAndGet() }
            .onEach { log.info { "count down $it" } }
            .take(10)

    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
