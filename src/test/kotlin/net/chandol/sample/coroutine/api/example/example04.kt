package net.chandol.sample.coroutine.api.example

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

// 비동기 function을 만들어보자
fun main() = runBlocking {
    val num1000 = calcNum(1000)
    val num2000 = calcNum(2000)

    val calc = num1000 + num2000
    log("$calc")
}

private fun calcNum(num: Int): Int {
    log("input : $num 계산 시작")
    Thread.sleep(num.toLong())
    return num
}
