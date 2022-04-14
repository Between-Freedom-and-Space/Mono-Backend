package com.between_freedom_and_space.mono_backend.auth.security.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.auth.security.JWTProcessor
import com.between_freedom_and_space.mono_backend.auth.security.models.JWTParams
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority.Companion.USER_ID_ALIAS
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority.Companion.USER_NAME_ALIAS
import com.between_freedom_and_space.mono_backend.util.extensions.ifNotNull
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaInstant
import java.util.Date

class HS256Processor: JWTProcessor {

    override fun createToken(authority: UserAuthority, params: JWTParams): String {
        val expiresAtDate = Date.from(params.expiresAt.toInstant(TimeZone.UTC).toJavaInstant())
        return JWT.create().apply {
            withAudience(params.audience)
            withIssuer(params.issuer)
            withClaim(USER_ID_ALIAS, authority.userId)
            withClaim(USER_NAME_ALIAS, authority.userName)
            withExpiresAt(expiresAtDate)

            params.subject.ifNotNull { subject ->
                withSubject(subject)
            }
        }.sign(Algorithm.HMAC256(params.secret))
    }

    override fun parseUserAuthority(token: DecodedJWT): UserAuthority {
        val userId = token.getClaim(USER_ID_ALIAS)?.asLong()
            ?: throw InvalidTokenException("Claim: $USER_ID_ALIAS not presented", token.token)
        val userName = token.getClaim(USER_NAME_ALIAS)?.asString()
            ?: throw InvalidTokenException("Claim: $USER_NAME_ALIAS not presented", token.token)

        return UserAuthority(userId, userName)
    }
}