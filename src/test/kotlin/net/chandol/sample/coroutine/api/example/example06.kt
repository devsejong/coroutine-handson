package net.chandol.sample.coroutine.api.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

// 배운걸 활용해서 비동기로 바꾸어보아요.
fun main() = runBlocking {
    val result = IntRange(0, 10)
        .map { blockingCalc(it) }
        .map { nonBlockingCalc(it) }
        .apply { this.forEachIndexed { idx, result -> complexLog("$idx = $result") } }
        .sum()

    log("최종 결과!! $result")
}

// 아래부터는 건드리지 마시오
private fun blockingCalc(num: Int): Int {
    log("blockingCalc $num")
    Thread.sleep(1000)
    return num * num
}

private suspend fun nonBlockingCalc(num: Int): Int {
    log("nonBlockingCalc $num")
    delay(2000)
    return num * num
}

private suspend fun complexLog(msg: String) {
    delay(2000)
    log(msg)
}
