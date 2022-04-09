package com.between_freedom_and_space.mono_backend.auth.api.routing

import com.between_freedom_and_space.mono_backend.auth.api.mappers.TokenVerifyResultToVerifyResponseMapper
import com.between_freedom_and_space.mono_backend.auth.api.models.TokenVerifyResultResponse
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.service.TokenAuthService
import com.between_freedom_and_space.mono_backend.auth.service.UserAuthService
import com.between_freedom_and_space.mono_backend.auth.util.AuthConstants
import com.between_freedom_and_space.mono_backend.auth.util.AuthConstants.TOKEN_HEADER_NAME
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

internal fun Application.authRouting() {
    val basePath = "/auth"

    routing {
        val tokenPath = "$basePath/token"

        val authService by inject<TokenAuthService>()

        val tokenMapper by inject<ModelMapper<TokenVerifyResult, TokenVerifyResultResponse>>(TokenVerifyResultToVerifyResponseMapper::class.java)

        post("$tokenPath/verifyAccess") {
            val accessToken = call.request.header(TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Access token not presented")

            val verifyResult = authService.verifyAccessToken(accessToken)

            val verifyResponse = tokenMapper.map(verifyResult).copy(tokenType = "Access token")
            val response = Response.ok(verifyResponse)

            call.respond(response)
        }

        post("$tokenPath/verifyRefresh") {
            val refreshToken = call.request.header(TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Refresh token not presented")

            val verifyResult = authService.verifyRefreshToken(refreshToken)

            val verifyResponse = tokenMapper.map(verifyResult)
            val response = Response.ok(verifyResponse)

            call.respond(response)
        }

        post("$tokenPath/refreshAccess") {
            val refreshToken = call.request.header(TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Refresh token not presented")

            val newAccessToken = authService.refreshAccessToken(refreshToken)

            call.response.header(TOKEN_HEADER_NAME, newAccessToken)
            call.respond(Unit)
        }
    }

    routing {
        val userPath = "$basePath/user"

        val userService by inject<UserAuthService>(UserAuthService::class.java)

        patch("$userPath/register") {

        }

        post("$userPath/authenticate") {

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

