package net.chandol.sample.coroutine.api.etc

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class ByteCode {
    suspend fun sample() = coroutineScope {
        val num1 = async {
            println("num1 시작")
            delay(2000L)
            println("num1 종료")
            100
        }

        val num2 = async {
            println("num2 시작")
            delay(1000L)
            println("num2 종료")
            200
        }

        val calc = num1.await() + num2.await()
        println("$calc")
    }
}
