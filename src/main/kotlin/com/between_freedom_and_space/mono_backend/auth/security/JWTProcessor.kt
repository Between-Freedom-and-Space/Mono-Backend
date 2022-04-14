package com.between_freedom_and_space.mono_backend.auth.security

import com.auth0.jwt.interfaces.DecodedJWT
import com.between_freedom_and_space.mono_backend.auth.security.models.JWTParams
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority

interface JWTProcessor {

    fun createToken(authority: UserAuthority, params: JWTParams): String
    
    fun parseUserAuthority(token: DecodedJWT): UserAuthority
}