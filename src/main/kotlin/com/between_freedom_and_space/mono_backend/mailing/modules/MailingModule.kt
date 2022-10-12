package com.between_freedom_and_space.mono_backend.mailing.modules

import com.between_freedom_and_space.mono_backend.mailing.plugin.config.emailServerConfiguration
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import org.koin.dsl.module

private val componentsModule = module {
    val application by inject<Application>()
    single { application.emailServerConfiguration() }

}

val mailingModule = module {

}