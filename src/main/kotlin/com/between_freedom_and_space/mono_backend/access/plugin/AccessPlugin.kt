package com.between_freedom_and_space.mono_backend.access.plugin

import com.between_freedom_and_space.mono_backend.access.api.routing.accessExceptionHandling
import com.between_freedom_and_space.mono_backend.access.api.routing.accessRouting
import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticatePlugin
import io.ktor.server.application.*

fun Application.addAccessPlugin() {
    install(AuthenticatePlugin)

    accessExceptionHandling()
    accessRouting()
}