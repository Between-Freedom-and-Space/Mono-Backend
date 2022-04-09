package com.between_freedom_and_space.mono_backend.auth.api.mappers

import com.between_freedom_and_space.mono_backend.auth.api.models.TokenVerifyResultResponse
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class TokenVerifyResultToVerifyResponseMapper: ModelMapper<TokenVerifyResult, TokenVerifyResultResponse> {

    companion object {

        private const val VALID = "Valid"
        private const val INVALID = "Invalid"
        private const val EXPIRED = "Expired"
    }

    override fun map(original: TokenVerifyResult): TokenVerifyResultResponse {
        val tokenType = "Token"
        return when (original) {
            is TokenVerifyResult.Valid -> TokenVerifyResultResponse(tokenType, VALID)
            is TokenVerifyResult.Invalid -> TokenVerifyResultResponse(tokenType, INVALID)
            is TokenVerifyResult.Expired -> TokenVerifyResultResponse(tokenType, EXPIRED)
        }
    }
}