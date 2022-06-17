package com.between_freedom_and_space.mono_backend.access.api.routing

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.accessInformationRouting() {
    val basePath = "/access"

    routing {
        val roleBasePath = "$basePath/role"

        get("$roleBasePath/all") {

        }

        get("$roleBasePath/{id}") {

        }
    }

    routing {
        val ruleBasePath = "$basePath/rule"

        get("$ruleBasePath/all") {

        }

        get("$ruleBasePath/{id}") {

        }
    }
}

