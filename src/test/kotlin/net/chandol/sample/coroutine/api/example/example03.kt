package net.chandol.sample.coroutine.api.example

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

// async를 사용해보자
fun main() = runBlocking {
    val num1 = 100
    delay(1000)

    val num2 = 200
    delay(1000)

    val calc = num1 + num2
    log("$calc")
}
