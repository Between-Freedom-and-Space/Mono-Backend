package com.between_freedom_and_space.mono_backend.auth.components.plugin.impl

import com.between_freedom_and_space.mono_backend.auth.components.TokenParser
import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticateProcessor
import com.between_freedom_and_space.mono_backend.auth.components.plugin.util.userAuthorityAttributeKey
import com.between_freedom_and_space.mono_backend.auth.service.UserProfileAuthService
import com.between_freedom_and_space.mono_backend.auth.util.AuthConstants
import io.ktor.server.request.*
import io.ktor.util.*

class TokenAuthenticateProcessor(
    private val tokenVerifier: TokenVerifier,
    private val tokenParser: TokenParser,
    private val userAuthService: UserProfileAuthService,
): AuthenticateProcessor {

    override fun intercept(request: ApplicationRequest, attributes: Attributes) {
        val tokenHeader = request.header(AuthConstants.TOKEN_HEADER_NAME)
            ?: throw AuthenticateException("Access token is missing")
        val verifyResult = tokenVerifier.verifyAccessToken(tokenHeader)

        if (verifyResult is TokenVerifyResult.Expired) {
            throw AuthenticateException("Access token is expired")
        }
        if (verifyResult is TokenVerifyResult.Invalid) {
            throw AuthenticateException("Access token is invalid")
        }
        verifyResult as TokenVerifyResult.Valid

        val authority = tokenParser.parseToken(verifyResult.decodedToken)
        val userId = authority.userId
        if (!userAuthService.profileIsValid(userId)) {
            throw AuthenticateException("Access token is invalid")
        }

        attributes.put(userAuthorityAttributeKey, authority)
    }
}