package com.between_freedom_and_space.mono_backend.auth.components.plugin

import io.ktor.server.request.*

interface AuthenticateProcessor {

    fun intercept(request: ApplicationRequest)
}