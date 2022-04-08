package com.between_freedom_and_space.mono_backend.auth.components.models

import com.auth0.jwt.interfaces.DecodedJWT

sealed class TokenVerifyResult {

    data class Valid(val decodedToken: DecodedJWT): TokenVerifyResult()

    object Invalid: TokenVerifyResult()

    object Expired: TokenVerifyResult()
}