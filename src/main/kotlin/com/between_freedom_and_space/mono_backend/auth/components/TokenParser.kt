package com.between_freedom_and_space.mono_backend.auth.components

import com.auth0.jwt.interfaces.DecodedJWT
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority

interface TokenParser {

    fun parseToken(token: DecodedJWT): UserAuthority
}