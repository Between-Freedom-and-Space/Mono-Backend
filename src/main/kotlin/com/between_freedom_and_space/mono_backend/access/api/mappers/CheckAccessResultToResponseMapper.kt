package com.between_freedom_and_space.mono_backend.access.api.mappers

import com.between_freedom_and_space.mono_backend.access.api.models.HasAccessResponse
import com.between_freedom_and_space.mono_backend.access.service.models.RuleCheckResult
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class CheckAccessResultToResponseMapper: ModelMapper<RuleCheckResult, HasAccessResponse> {

    override fun map(original: RuleCheckResult): HasAccessResponse {
        return HasAccessResponse(
            result = original.checkResult.name,
            pathPattern = original.rule?.pathPattern
        )
    }
}