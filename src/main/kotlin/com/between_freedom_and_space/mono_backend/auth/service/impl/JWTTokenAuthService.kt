package com.between_freedom_and_space.mono_backend.auth.service.impl

import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.service.TokenAuthService

class JWTTokenAuthService(
    private val tokenProducer: TokenProducer,
    private val tokenVerifier: TokenVerifier,
): TokenAuthService {

    override fun refreshAccessToken(refreshToken: String): String {
        TODO("Not yet implemented")
    }

    override fun verifyAccessToken(token: String): TokenVerifyResult {
        TODO("Not yet implemented")
    }

    override fun verifyRefreshToken(token: String): TokenVerifyResult {
        TODO("Not yet implemented")
    }

    override fun authenticateUser(nickname: String, passwordEncoded: String): TokenProducer.ProducerResult {
        TODO("Not yet implemented")
    }
}