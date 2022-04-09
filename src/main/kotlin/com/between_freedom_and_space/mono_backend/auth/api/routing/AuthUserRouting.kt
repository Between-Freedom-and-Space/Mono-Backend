package com.between_freedom_and_space.mono_backend.auth.api.routing

import com.between_freedom_and_space.mono_backend.auth.service.UserAuthService
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.authUserRouting() {
    val basePath = "/auth/user"

    val userService by inject<UserAuthService>()

    routing {

        patch("$basePath/register") {

        }

        post("$basePath/authenticate") {

        }

        delete("$basePath/delete") {

        }
    }
}