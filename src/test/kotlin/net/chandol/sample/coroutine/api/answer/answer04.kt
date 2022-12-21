package net.chandol.sample.coroutine.api.answer

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

fun main() = runBlocking {
    val num1000 = async { calcNum(1000) }
    val num2000 = async { calcNum(2000) }

    val calc = num1000.await() + num2000.await()
    log("$calc")
}

private suspend fun calcNum(num: Int): Int {
    log("input : $num 계산 시작")
    delay(num.toLong())
    return num
}
