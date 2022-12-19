package net.chandol.sample.coroutine.api.answer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

fun main() = runBlocking {
    val num1000 = async(Dispatchers.IO) { calcWithSleep(1000) }
    val num2000 = async(Dispatchers.IO) { calcWithSleep(2000) }

    val calc = num1000.await() + num2000.await()
    log("$calc")
}

private fun calcWithSleep(num: Int): Int {
    log("input : $num 계산 시작")
    Thread.sleep(1000L)
    log("input : $num 계산 완료")
    return num
}
