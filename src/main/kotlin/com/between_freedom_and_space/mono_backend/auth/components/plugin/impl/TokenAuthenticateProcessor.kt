package com.between_freedom_and_space.mono_backend.auth.components.plugin.impl

import com.between_freedom_and_space.mono_backend.auth.components.TokenParser
import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticateProcessor
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import com.between_freedom_and_space.mono_backend.auth.util.AuthConstants
import io.ktor.server.request.*
import io.ktor.util.*
import kotlin.reflect.jvm.jvmName

class TokenAuthenticateProcessor(
    private val tokenVerifier: TokenVerifier,
    private val tokenParser: TokenParser,
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

        addUserAuthorities(verifyResult as TokenVerifyResult.Valid, attributes)
    }

    private fun addUserAuthorities(verifyResult: TokenVerifyResult.Valid, attributes: Attributes) {
        val authority = tokenParser.parseToken(verifyResult.decodedToken)
        val key = AttributeKey<UserAuthority>(UserAuthority::class.jvmName)
        attributes.put(key, authority)
    }
}