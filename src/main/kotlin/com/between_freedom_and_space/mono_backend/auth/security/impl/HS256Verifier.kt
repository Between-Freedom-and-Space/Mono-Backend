package com.between_freedom_and_space.mono_backend.auth.security.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.between_freedom_and_space.mono_backend.auth.security.JWTVerifier
import com.between_freedom_and_space.mono_backend.auth.security.Verifier
import com.between_freedom_and_space.mono_backend.auth.security.models.JWTParams
import com.between_freedom_and_space.mono_backend.util.extensions.ifNotNull

class HS256Verifier: JWTVerifier {

    override fun createVerifier(params: JWTParams): Verifier {
        return JWT.require(Algorithm.HMAC256(params.secret)).apply {
            withAudience(params.audience)
            withIssuer(params.issuer)

            params.subject.ifNotNull { subject ->
                withSubject(subject)
            }
        }.build()
    }
}