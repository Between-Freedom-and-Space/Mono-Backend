package com.between_freedom_and_space.mono_backend.mailing.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.mailing.api.models.VerifyCodeResponse
import com.between_freedom_and_space.mono_backend.mailing.service.models.VerificationCheckResult

class VerifyResultToResponseMapper: ModelMapper<VerificationCheckResult, VerifyCodeResponse> {

    override fun map(original: VerificationCheckResult): VerifyCodeResponse {
        return when (original) {
            is VerificationCheckResult.Invalid -> VerifyCodeResponse("Invalid Code")
            is VerificationCheckResult.NotFound -> VerifyCodeResponse("Not Found")
            is VerificationCheckResult.Valid -> VerifyCodeResponse("Validated")
        }
    }
}