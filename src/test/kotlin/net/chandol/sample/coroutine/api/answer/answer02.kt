package net.chandol.sample.coroutine.api.answer

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

fun main() = runBlocking {
    launch {
        delay(1000L)
        log("1111")
    }

    launch {
        delay(1000L)
        log("2222")
    }

    log("3333")
}
