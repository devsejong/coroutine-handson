package net.chandol.sample.coroutine.api.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

// launch로 동시에 로그 실행해보기
fun main() = runBlocking {
    delay(1000)
    log("1111")

    delay(1000)
    log("2222")

    log("3333")
}
