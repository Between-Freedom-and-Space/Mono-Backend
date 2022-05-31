package com.between_freedom_and_space.mono_backend.app

import com.between_freedom_and_space.mono_backend.app.config.configureDatabase
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun main() {

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        val koin = startKoin {
            modules(applicationModule, module {
                single { this@embeddedServer }
            })
        }

        main(koin)
    }.start(wait = true)
}
