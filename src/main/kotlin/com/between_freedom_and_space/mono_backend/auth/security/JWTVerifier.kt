package com.between_freedom_and_space.mono_backend.auth.security

import com.between_freedom_and_space.mono_backend.auth.security.models.JWTParams

typealias Verifier = com.auth0.jwt.interfaces.JWTVerifier

interface JWTVerifier {

    fun createVerifier(params: JWTParams): Verifier
}