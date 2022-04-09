package com.between_freedom_and_space.mono_backend.auth.api.routing

import com.between_freedom_and_space.mono_backend.auth.api.models.TokenVerifyResultResponse
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.service.TokenAuthService
import com.between_freedom_and_space.mono_backend.auth.util.AuthConstants
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Application.authTokenRouting() {
    val basePath = "/auth/token"

    val authService by inject<TokenAuthService>()
    val tokenMapper by inject<ModelMapper<TokenVerifyResult, TokenVerifyResultResponse>>()

    routing {

        post("$basePath/verifyAccess") {
            val accessToken = call.request.header(AuthConstants.TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Access token not presented")

            val verifyResult = authService.verifyAccessToken(accessToken)

            val verifyResponse = tokenMapper.map(verifyResult).copy(tokenType = "Access token")
            val response = Response.ok(verifyResponse)

            call.respond(response)
        }

        post("$basePath/verifyRefresh") {
            val refreshToken = call.request.header(AuthConstants.TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Refresh token not presented")

            val verifyResult = authService.verifyRefreshToken(refreshToken)

            val verifyResponse = tokenMapper.map(verifyResult).copy(tokenType = "Refresh token")
            val response = Response.ok(verifyResponse)

            call.respond(response)
        }

        post("$basePath/refreshAccess") {
            val refreshToken = call.request.header(AuthConstants.TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Refresh token not presented")

            val newAccessToken = authService.refreshAccessToken(refreshToken)

            call.response.header(AuthConstants.TOKEN_HEADER_NAME, newAccessToken)
            call.respond(Unit)
        }
    }
}