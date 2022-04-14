package com.between_freedom_and_space.mono_backend.auth.components.impl

import com.auth0.jwt.interfaces.DecodedJWT
import com.between_freedom_and_space.mono_backend.auth.components.TokenParser
import com.between_freedom_and_space.mono_backend.auth.security.JWTProcessor
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority

class JWTTokenParser(
    private val jwtProcessor: JWTProcessor,
): TokenParser {

    override fun parseToken(token: DecodedJWT): UserAuthority {
        return jwtProcessor.parseUserAuthority(token)
    }
}