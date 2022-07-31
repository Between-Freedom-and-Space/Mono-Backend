package com.between_freedom_and_space.mono_backend.auth.components.plugin

import io.ktor.server.request.*
import io.ktor.util.*

interface AuthenticateProcessor {

    fun intercept(request: ApplicationRequest, attributes: Attributes)

    // TODO(Refactor authenticate plugin)
    fun validateOrThrow(request: ApplicationRequest)
}