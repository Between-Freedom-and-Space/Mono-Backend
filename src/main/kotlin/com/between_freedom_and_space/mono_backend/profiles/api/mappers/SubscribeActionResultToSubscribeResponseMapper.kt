package com.between_freedom_and_space.mono_backend.profiles.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.models.SubscribeActionResponse
import com.between_freedom_and_space.mono_backend.profiles.services.models.SubscribeActionResult

class SubscribeActionResultToSubscribeResponseMapper: ModelMapper<SubscribeActionResult, SubscribeActionResponse> {

    override fun map(original: SubscribeActionResult): SubscribeActionResponse {
        return SubscribeActionResponse(
            result = original.result.name,
            message = original.message,
        )
    }
}