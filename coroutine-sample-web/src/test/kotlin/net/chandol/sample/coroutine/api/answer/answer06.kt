package net.chandol.sample.coroutine.api.answer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

// 최대한 빠르게 만들어보아요
fun main() = runBlocking {
    val result = IntRange(0, 10)
        .map { async(Dispatchers.IO) { blockingCalc(it) } }
        .awaitAll()
        .map { async { nonBlockingCalc(it) } }
        .awaitAll()
        .apply { this.forEachIndexed { idx, result -> launch { complexLog("$idx = $result") } } }
        .sum()

    println(result)
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
