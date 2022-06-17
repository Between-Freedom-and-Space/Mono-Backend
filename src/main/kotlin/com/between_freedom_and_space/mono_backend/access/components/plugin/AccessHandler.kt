package com.between_freedom_and_space.mono_backend.access.components.plugin

import io.ktor.server.request.*
import io.ktor.util.*

interface AccessHandler {

    fun intercept(request: ApplicationRequest, attributes: Attributes)
}