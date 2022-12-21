package net.chandol.sample.coroutine.api.answer

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

fun main() = runBlocking {
    val num1 = async {
        log("num1 계산 시작")
        delay(2000L)
        log("num1 계산 완료")
        100
    }

    val num2 = async {
        log("num2 계산 시작")
        delay(1000L)
        log("num2 계산 완료")
        200
    }

    val calc = num1.await() + num2.await()
    log("$calc")
}
