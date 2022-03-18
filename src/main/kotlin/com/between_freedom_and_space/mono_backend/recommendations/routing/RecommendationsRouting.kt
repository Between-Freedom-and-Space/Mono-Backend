package com.between_freedom_and_space.mono_backend.recommendations.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.recommendationsRouting() {
    val basePath = "/recommendations"

    routing {

        get("$basePath/personal/{id}") {

        }

        get("$basePath/most/watched") {

        }

        get("$basePath/most/reacted") {

        }

        get("$basePath/most/commented") {

        }
    }
}