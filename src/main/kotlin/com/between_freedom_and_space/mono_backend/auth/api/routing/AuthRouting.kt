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

        patch("$userPath/delete") {

        }
    }

    routing {
        val settingPath = "$basePath/settings"

        get("$settingPath/{id}") {

        }

        get("$settingPath/all") {

        }

        patch("$settingPath/update/accessTokenLifetime") {

        }

        patch("$settingPath/update/refreshTokenLifetime") {

        }
    }
}

