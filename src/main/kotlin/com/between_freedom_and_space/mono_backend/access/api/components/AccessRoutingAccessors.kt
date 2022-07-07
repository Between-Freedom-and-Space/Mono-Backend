package com.between_freedom_and_space.mono_backend.access.api.components

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.accessRoutingAccessors() {
    val basePath = "/access"

    routing {

        routingAccessor("$basePath/role") { accessData ->
            AccessVerifyResult.ACCESSED
        }
    }
}