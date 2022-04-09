package com.between_freedom_and_space.mono_backend.auth.components

import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult

interface TokenVerifier {

    fun verifyAccessToken(token: String): TokenVerifyResult

    fun verifyRefreshToken(token: String): TokenVerifyResult
}