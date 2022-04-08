package com.between_freedom_and_space.mono_backend.auth.components.impl

import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult

class JWTTokenProducer: TokenProducer {

    override fun produceAccessToken(refreshToken: String): String {
        TODO("Not yet implemented")
    }

    override fun produceTokens(userId: Long, nickName: String): ProducerResult {
        TODO("Not yet implemented")
    }
}