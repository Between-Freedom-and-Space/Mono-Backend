package com.between_freedom_and_space.mono_backend.auth.plugins

import com.between_freedom_and_space.mono_backend.auth.api.routing.authExceptionHandling
import com.between_freedom_and_space.mono_backend.auth.api.routing.authSettingsRouting
import com.between_freedom_and_space.mono_backend.auth.api.routing.authTokenRouting
import com.between_freedom_and_space.mono_backend.auth.api.routing.authUserRouting
import io.ktor.server.application.*

fun Application.addAuthPlugin() {

    authTokenRouting()
    authSettingsRouting()
    authUserRouting()

    authExceptionHandling()
}
