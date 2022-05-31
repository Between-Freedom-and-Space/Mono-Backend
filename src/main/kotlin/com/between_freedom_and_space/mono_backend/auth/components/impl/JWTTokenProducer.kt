package com.between_freedom_and_space.mono_backend.auth.components.impl

import com.auth0.jwt.interfaces.DecodedJWT
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.auth.components.util.TokenConstants
import com.between_freedom_and_space.mono_backend.auth.plugins.config.properties.AuthProperties
import com.between_freedom_and_space.mono_backend.auth.security.JWTProcessor
import com.between_freedom_and_space.mono_backend.auth.security.models.JWTParams
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNowPlus

class JWTTokenProducer(
    private val jwtProcessor: JWTProcessor,
    private val properties: AuthProperties,
): TokenProducer {

    companion object {

        private const val REFRESH_DURATION_MILLIS = 1000L * 60 * 60 * 24 * 30
        private const val ACCESS_DURATION_MILLIS = 1000L * 60 * 60
    }

    override fun produceAccessToken(refreshToken: DecodedJWT): String {
        val claims = refreshToken.claims
        val userId = claims[UserAuthority.USER_ID_ALIAS]?.asLong()
            ?: throw InvalidTokenException("Claim: ${UserAuthority.USER_ID_ALIAS} not presented", refreshToken.token)
        val nickName = claims[UserAuthority.USER_NAME_ALIAS]?.asString()
            ?: throw InvalidTokenException("Claim: ${UserAuthority.USER_NAME_ALIAS} not presented", refreshToken.token)

        val authority = UserAuthority(userId, nickName)
        val params = JWTParams(
            secret = properties.tokenSecret,
            audience = properties.tokenAudience,
            issuer = properties.tokenIssuer,
            expiresAt = localDateTimeNowPlus(ACCESS_DURATION_MILLIS),
            subject = TokenConstants.ACCESS_TOKEN_SUBJECT
        )

        return jwtProcessor.createToken(authority, params)
    }

    override fun produceTokens(userId: Long, nickName: String): ProducerResult {
        val authority = UserAuthority(userId, nickName)
        val params = JWTParams(
            secret = properties.tokenSecret,
            audience = properties.tokenAudience,
            issuer = properties.tokenIssuer
        )

        val accessToken = jwtProcessor.createToken(
            authority = authority,
            params = params.copy(
                expiresAt = localDateTimeNowPlus(ACCESS_DURATION_MILLIS),
                subject = TokenConstants.ACCESS_TOKEN_SUBJECT
            )
        )
        val refreshToken = jwtProcessor.createToken(
            authority = authority,
            params = params.copy(
                expiresAt = localDateTimeNowPlus(REFRESH_DURATION_MILLIS),
                subject = TokenConstants.REFRESH_TOKEN_SUBJECT
            )
        )

        return ProducerResult(accessToken, refreshToken)
    }
}