package com.between_freedom_and_space.mono_backend.access.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.accessInteractionRouting() {
    val basePath = "/access"

    routing {
        val roleBasePath = "$basePath/role"
    }

    routing {
        val ruleBasePath = "$basePath/rule"

        patch("$ruleBasePath/create") {

        }

        put("$ruleBasePath/{id}/update") {

        }

        delete("$ruleBasePath/{id}/delete") {

        }
    }
}