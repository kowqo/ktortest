package com.genshin

import com.genshin.features.login.configureLoginRouting
import com.genshin.features.registration.configureRegisterRouting
import io.ktor.server.application.*
import com.genshin.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {


    configureSerialization()
//    configureDatabases()
//    configureHTTP()
//    configureSecurity()
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
}
