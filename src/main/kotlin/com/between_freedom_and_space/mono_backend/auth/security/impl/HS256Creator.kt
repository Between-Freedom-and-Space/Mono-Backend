package com.between_freedom_and_space.mono_backend.auth.security.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.between_freedom_and_space.mono_backend.auth.security.JWTCreator
import com.between_freedom_and_space.mono_backend.auth.security.models.JWTParams
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority.Companion.USER_ID_ALIAS
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority.Companion.USER_NAME_ALIAS
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaInstant
import java.util.Date

class HS256Creator: JWTCreator {

    override fun createToken(authority: UserAuthority, params: JWTParams): String {
        val expiresAtDate = Date.from(params.expiresAt.toInstant(TimeZone.UTC).toJavaInstant())
        return JWT.create()
            .withAudience(params.audience)
            .withIssuer(params.issuer)
            .withClaim(USER_ID_ALIAS, authority.userId)
            .withClaim(USER_NAME_ALIAS, authority.userName)
            .withExpiresAt(expiresAtDate)
            .sign(Algorithm.HMAC256(params.secret))
    }
}