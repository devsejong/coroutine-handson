package net.chandol.sample.coroutine.api.example

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

// 동기를 비동기로 처리해보자
fun main() = runBlocking {
    val num1000 = calcNum(1000)
    val num2000 = calcNum(2000)

    val calc = num1000 + num2000
    log("$calc")
}

private fun calcNum(num: Int): Int {
    log("input : $num 계산 시작")
    // delay(1000)
    return num
}
