package com.between_freedom_and_space.mono_backend.popular.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.popularRouting() {
    val basePath = "/popular"

    routing {

        get("$basePath/posts") {

        }

        get("$basePath/tags") {

        }

        get("$basePath/comments") {

        }

        get("$basePath/profiles") {

        }
    }
}