package net.chandol.sample.coroutine.api.util

import java.time.LocalDateTime

fun log(msg: String) = println("${LocalDateTime.now()} [${Thread.currentThread().name}] $msg")
