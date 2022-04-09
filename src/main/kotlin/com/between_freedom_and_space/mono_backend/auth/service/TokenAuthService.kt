package com.between_freedom_and_space.mono_backend.auth.service

import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult

interface TokenAuthService {

    fun refreshAccessToken(refreshToken: String): String

    fun verifyAccessToken(token: String): TokenVerifyResult

    fun verifyRefreshToken(token: String): TokenVerifyResult

    fun authenticateUser(nickname: String, passwordEncoded: String): ProducerResult
}