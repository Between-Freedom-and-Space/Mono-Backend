package com.between_freedom_and_space.mono_backend.auth.api.routing

import com.between_freedom_and_space.mono_backend.auth.api.models.AuthenticateUserRequest
import com.between_freedom_and_space.mono_backend.auth.api.models.AuthenticateUserResponse
import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserRequest
import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserResponse
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.auth.service.UserAuthService
import com.between_freedom_and_space.mono_backend.auth.util.AuthConstants
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.models.UserProfileModel
import com.between_freedom_and_space.mono_backend.util.extensions.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.authUserRouting() {
    val basePath = "/auth/user"

    val userService by inject<UserAuthService>()

    val registerMapper by inject<ModelMapper<UserProfileModel, RegisterUserResponse>>()
    val authenticateMapper by inject<ModelMapper<ProducerResult, AuthenticateUserResponse>>()

    routing {

        patch("$basePath/register") {
            val request = validateAndReceiveRequest<RegisterUserRequest>()

            val result = userService.registerNewUser(request)

            val registerResponse = registerMapper.map(result)
            val response = Response.ok(registerResponse)

            sendResponse(response)
        }

        post("$basePath/authenticate") {
            val request = validateAndReceiveRequest<AuthenticateUserRequest>()

            val result = userService.authenticateUser(request.nickname, request.passwordEncoded)

            val authenticateResult = authenticateMapper.map(result)
            val response = Response.ok(authenticateResult)

            sendResponse(response)
        }

        delete("$basePath/delete") {
            val accessToken = getRequestHeader(AuthConstants.TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Access token not presented", "Empty token")

            userService.deleteUser(accessToken)

            sendResponse(Response.accepted())
        }
    }
}