package com.between_freedom_and_space.mono_backend.access.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.netty.util.concurrent.Promise
import kotlinx.coroutines.async

internal fun Application.accessInteractionRouting() {
    val basePath = "/access"

    routing {
        val roleBasePath = "$basePath/role"

    }

    routing {
        val ruleBasePath = "$basePath/rule"

    }
}