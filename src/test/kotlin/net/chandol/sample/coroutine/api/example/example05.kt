package net.chandol.sample.coroutine.api.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

// 동기식 코드도 비동기로 처리해보자
fun main() = runBlocking {
    val num1000 = calcWithSleep(1000)
    val num2000 = calcWithSleep(2000)

    val calc = num1000 + num2000
    log("$calc")
}

private fun calcWithSleep(num: Int): Int {
    log("input : $num 계산 시작")
    Thread.sleep(1000L)
    log("input : $num 계산 완료")
    return num
}
