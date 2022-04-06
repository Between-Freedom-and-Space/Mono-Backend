package com.between_freedom_and_space.mono_backend.auth.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.authRouting() {
    val basePath = "/auth"

    routing {
        val tokenPath = "$basePath/token"

        post("$tokenPath/verifyAccess") {

        }

        post("$tokenPath/verifyRefresh") {

        }

        post("$tokenPath/refreshAccess") {

        }
    }

    routing {
        val userPath = "$basePath/user"

        patch("$userPath/register") {

        }

        delete("$userPath/delete") {

        }
    }

    routing {
        val settingsPath = "$basePath/settings"

        get("$settingsPath/{id}") {

        }

        get("$settingsPath/all") {

        }

        patch("$settingsPath/{id}/update/accessTokenLifetime") {

        }

        patch("$settingsPath/{id}/update/refreshTokenLifetime") {

        }
    }
}

