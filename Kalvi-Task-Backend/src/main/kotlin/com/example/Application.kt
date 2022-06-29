package com.example

import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureCors()
    configureRouting()
    configureMonitoring()
    configureSerialization()
}