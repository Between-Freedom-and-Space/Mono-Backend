package com.between_freedom_and_space.mono_backend.auth.api.routing

import com.between_freedom_and_space.mono_backend.auth.api.models.AuthenticateUserRequest
import com.between_freedom_and_space.mono_backend.auth.api.models.AuthenticateUserResponse
import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserRequest
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.auth.modules.qualifiers.AuthModelMapperQualifier
import com.between_freedom_and_space.mono_backend.auth.service.AuthService
import com.between_freedom_and_space.mono_backend.auth.util.AuthConstants
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.models.ProfileModel
import com.between_freedom_and_space.mono_backend.profiles.modules.qualifiers.ProfilesMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.util.extensions.getRequestHeader
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.authUserRouting() {
    val basePath = "/auth/user"

    val userService by inject<AuthService>()

    val registerMapper by inject<ModelMapper<BaseProfileModel, ProfileModel>>(
        named(ProfilesMappersQualifiers.BASE_PROFILE_TO_PROFILE_MODEL)
    )
    val authenticateMapper by inject<ModelMapper<ProducerResult, AuthenticateUserResponse>>(
        named(AuthModelMapperQualifier.PRODUCER_RESULT_TO_AUTHENTICATE_RESPONSE)
    )

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