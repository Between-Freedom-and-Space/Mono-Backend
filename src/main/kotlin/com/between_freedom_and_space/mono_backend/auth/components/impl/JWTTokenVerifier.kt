package com.between_freedom_and_space.mono_backend.auth.components.impl

import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult

class JWTTokenVerifier: TokenVerifier {

    override fun verifyAccessToken(token: String): TokenVerifyResult {
        TODO("Not yet implemented")
    }

    override fun verifyRefreshToken(token: String): TokenVerifyResult {
        TODO("Not yet implemented")
    }
}