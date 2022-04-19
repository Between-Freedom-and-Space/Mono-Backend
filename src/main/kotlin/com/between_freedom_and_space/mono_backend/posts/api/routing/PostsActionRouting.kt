package com.between_freedom_and_space.mono_backend.posts.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.postActionRouting() {
    val basePath = "/post"

    routing {

        post("$basePath/{id}/react") {

        }

        post("$basePath/{id}/comment") {

        }
    }

    routing {

        patch("$basePath/create") {

        }

        put("$basePath/{id}/update") {

        }

        delete("$basePath/{id}/delete") {

        }
    }
}