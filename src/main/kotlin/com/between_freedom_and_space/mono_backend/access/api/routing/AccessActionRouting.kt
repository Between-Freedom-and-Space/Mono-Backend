package com.between_freedom_and_space.mono_backend.access.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.accessActionRouting() {
    val basePath = "/access"

    routing {
        val roleBasePath = "$basePath/role"

        post("$roleBasePath/user/{nickname}/change") {

        }
    }

    routing {
        val ruleBasePath = "$basePath/rule"

        put("$ruleBasePath/{id}/give/access/to/user/{nickname}") {

        }

        delete("$ruleBasePath/{id}/remove/access/from/user/{nickname}") {

        }

        put("$ruleBasePath/{id}/give/access/to/role/{roleAlias}") {

        }

        delete("$ruleBasePath/{id}/remove/access/from/role/{roleAlias}") {

        }
    }
}