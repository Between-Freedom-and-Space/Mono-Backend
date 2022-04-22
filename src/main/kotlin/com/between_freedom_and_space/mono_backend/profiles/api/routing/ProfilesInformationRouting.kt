package com.between_freedom_and_space.mono_backend.profiles.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.profilesInformationRouting() {
    val basePath = "/profile"

    routing {

        get("$basePath/all") {

        }

        get("$basePath/{nickname}") {

        }

        get("$basePath/{nickname}/subscriptions") {

        }

        get("$basePath/{nickname}/subscribers") {

        }

        get("$basePath/{nickname}/posts") {

        }

        get("$basePath/{nickname}/comments") {

        }

        get("$basePath/{nickname}/tags") {

        }

        get("$basePath/{nickname}/reactions") {

        }
    }
}