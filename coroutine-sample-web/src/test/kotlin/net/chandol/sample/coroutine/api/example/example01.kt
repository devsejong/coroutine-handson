package net.chandol.sample.coroutine.api.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.chandol.sample.coroutine.api.util.log

// add to vm option -> "-Dkotlinx.coroutines.debug"
fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        log("World!") // print after delay
    }
    log("Hello") // main coroutine continues while a previous one is delayed
}

// 기본 스레드 사용시 OOM 발생함
//fun main() {
//    repeat(100_000) { // launch a lot of coroutines
//        thread {
//            Thread.sleep(2000L)
//            print(".")
//        }
//    }
//    log("finish")
//}

// 하지만 코루틴을 사용하면 OOM이 발생하지 않는다.
//fun main() = runBlocking {
//    // run task with coroutine
//    repeat(100_000) { // launch a lot of coroutines
//        launch {
//            delay(2000L)
//            print(".")
//        }
//    }
//}
