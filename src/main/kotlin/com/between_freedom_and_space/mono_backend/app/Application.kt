package com.between_freedom_and_space.mono_backend.app

import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun main() {

    embeddedServer(Netty, environment = applicationEngineEnvironment {
        config = HoconApplicationConfig(ConfigFactory.load())

        module { applicationModule(this) }

        connector {
            port = 8080
            host = "0.0.0.0"
        }
    }).start(wait = true)
}

private fun applicationModule(application: Application) {
    startKoin {
        modules(applicationModule, module {
            single { application }
        })
    }

    application.main()
}
