package com.between_freedom_and_space.mono_backend.auth.security

import com.between_freedom_and_space.mono_backend.auth.security.models.JWTParams
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority

interface JWTCreator {

    fun createToken(authority: UserAuthority, params: JWTParams): String
}