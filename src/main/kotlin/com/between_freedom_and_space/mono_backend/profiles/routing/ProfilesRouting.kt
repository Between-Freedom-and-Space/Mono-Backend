package com.between_freedom_and_space.mono_backend.profiles.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.profilesRouting() {
    val basePath = "/profiles"

    routing {

        get("$basePath/all") {

        }

        get("$basePath/{id}") {

        }

        get("$basePath/{id}/subscriptions") {

        }

        get("$basePath/{id}/subscribers") {

        }
    }

    routing {

        post("$basePath/create") {

        }

        post("$basePath/subscribe/{id}") {

        }
    }

    routing {

        put("$basePath/update/{id}/text") {

        }

        put {

        }
    }

    routing {

        patch {

        }

        patch {

        }
    }

    routing {

        delete("$basePath/{id}") {

        }

        delete {

        }
    }
}