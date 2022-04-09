package com.between_freedom_and_space.mono_backend.auth.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.authSettingsRouting() {
    val basePath = "/auth/settings"

    routing {

        get("$basePath/{id}") {

        }

        get("$basePath/all") {

        }

        patch("$basePath/{id}/update/accessTokenLifetime") {

        }

        patch("$basePath/{id}/update/refreshTokenLifetime") {

        }
    }
}