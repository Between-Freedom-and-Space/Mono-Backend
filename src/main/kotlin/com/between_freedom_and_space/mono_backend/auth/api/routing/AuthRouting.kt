package com.between_freedom_and_space.mono_backend.auth.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.authRouting() {
    val basePath = "/auth"

    routing {

        post("$basePath/token/validateRefresh") {

        }

        post("$basePath/token/validateAccess") {

        }
    }

    routing {

        patch("$basePath/user/register") {

        }

        patch("$basePath/user/delete") {

        }
    }
}

