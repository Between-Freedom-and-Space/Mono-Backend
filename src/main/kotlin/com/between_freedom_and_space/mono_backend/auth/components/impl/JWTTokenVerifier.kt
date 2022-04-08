package com.between_freedom_and_space.mono_backend.auth.components.impl

import com.auth0.jwt.exceptions.JWTVerificationException
import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.components.util.TokenConstants
import com.between_freedom_and_space.mono_backend.auth.models.AuthProperties
import com.between_freedom_and_space.mono_backend.auth.security.JWTVerifier
import com.between_freedom_and_space.mono_backend.auth.security.models.JWTParams

class JWTTokenVerifier(
    private val jwtVerifier: JWTVerifier,
    private val properties: AuthProperties
): TokenVerifier {

    override fun verifyAccessToken(token: String): TokenVerifyResult {
        return verifyWithSubject(token, TokenConstants.ACCESS_TOKEN_SUBJECT)
    }

    override fun verifyRefreshToken(token: String): TokenVerifyResult {
        return verifyWithSubject(token, TokenConstants.REFRESH_TOKEN_SUBJECT)
    }

    private fun verifyWithSubject(token: String, subject: String): TokenVerifyResult {
        val params = JWTParams(
            secret = properties.tokenSecret,
            audience = properties.tokenAudience,
            issuer = properties.tokenIssuer,
            subject = subject,
        )
        val verifier = jwtVerifier.createVerifier(params)

        return try {
            val decodedToken = verifier.verify(token)
            return TokenVerifyResult.Valid(decodedToken)
        } catch (ex: JWTVerificationException) {
            TokenVerifyResult.Invalid
        }
    }
}