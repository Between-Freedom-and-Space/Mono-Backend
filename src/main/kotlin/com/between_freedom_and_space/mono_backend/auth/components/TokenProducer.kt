package com.between_freedom_and_space.mono_backend.auth.components

import com.auth0.jwt.interfaces.DecodedJWT

interface TokenProducer {

    fun produceAccessToken(refreshToken: DecodedJWT): String

    fun produceTokens(userId: Long, nickName: String): ProducerResult

    data class ProducerResult(val accessToken: String, val refreshToken: String)
}