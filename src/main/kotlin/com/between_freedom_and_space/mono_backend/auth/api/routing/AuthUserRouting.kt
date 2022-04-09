package com.between_freedom_and_space.mono_backend.auth.api.routing

import com.between_freedom_and_space.mono_backend.auth.api.models.AuthenticateUserRequest
import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserRequest
import com.between_freedom_and_space.mono_backend.auth.service.UserAuthService
import com.between_freedom_and_space.mono_backend.auth.util.AuthConstants
import com.between_freedom_and_space.mono_backend.util.extensions.getRequestHeader
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.authUserRouting() {
    val basePath = "/auth/user"

    val userService by inject<UserAuthService>()

    routing {

        patch("$basePath/register") {
            val request = validateAndReceiveRequest<RegisterUserRequest>()

            val result = userService.registerNewUser(request)
        }

        post("$basePath/authenticate") {
            val request = validateAndReceiveRequest<AuthenticateUserRequest>()

        }

        delete("$basePath/delete") {
            val accessToken = getRequestHeader(AuthConstants.TOKEN_HEADER_NAME)
        }
    }
}