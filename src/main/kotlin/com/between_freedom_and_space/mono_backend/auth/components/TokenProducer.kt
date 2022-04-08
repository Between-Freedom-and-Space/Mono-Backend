package com.between_freedom_and_space.mono_backend.auth.components

interface TokenProducer {

    fun produceAccessToken(refreshToken: String): String

    fun produceTokens(userId: Long, nickName: String): ProducerResult

    data class ProducerResult(val accessToken: String, val refreshToken: String)
}