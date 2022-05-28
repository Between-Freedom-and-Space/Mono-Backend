package com.between_freedom_and_space.mono_backend.auth.api.routing

import com.between_freedom_and_space.mono_backend.auth.api.models.TokenVerifyResultResponse
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.modules.qualifiers.AuthModelMapperQualifier
import com.between_freedom_and_space.mono_backend.auth.service.TokenAuthService
import com.between_freedom_and_space.mono_backend.auth.util.AuthConstants
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.util.extensions.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.authTokenRouting() {
    val basePath = "/auth/token"

    val authService by inject<TokenAuthService>()
    val tokenMapper by inject<ModelMapper<TokenVerifyResult, TokenVerifyResultResponse>>(
        named(AuthModelMapperQualifier.TOKEN_VERIFY_RESULT_TO_RESPONSE)
    )

    routing {

        post("$basePath/verifyAccess") {
            val accessToken = getRequestHeader(AuthConstants.TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Access token not presented", "Empty token")

            val verifyResult = authService.verifyAccessToken(accessToken)

            val verifyResponse = tokenMapper.map(verifyResult).copy(tokenType = "Access token")
            val response = Response.ok(verifyResponse)

            sendResponse(response)
        }

        post("$basePath/verifyRefresh") {
            val refreshToken = getRequestHeader(AuthConstants.TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Refresh token not presented", "Empty token")

            val verifyResult = authService.verifyRefreshToken(refreshToken)

            val verifyResponse = tokenMapper.map(verifyResult).copy(tokenType = "Refresh token")
            val response = Response.ok(verifyResponse)

            sendResponse(response)
        }

        post("$basePath/refreshAccess") {
            val refreshToken = getRequestHeader(AuthConstants.TOKEN_HEADER_NAME)
                ?: throw InvalidTokenException("Refresh token not presented", "Empty token")

            val newAccessToken = authService.refreshAccessToken(refreshToken)

            appendResponseHeader(AuthConstants.TOKEN_HEADER_NAME, newAccessToken)
            sendEmptyResponse()
        }
    }
}