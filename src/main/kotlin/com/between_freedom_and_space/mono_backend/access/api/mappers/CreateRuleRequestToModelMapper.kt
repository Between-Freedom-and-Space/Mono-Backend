package com.between_freedom_and_space.mono_backend.access.api.mappers

import com.between_freedom_and_space.mono_backend.access.api.models.CreateRuleRequest
import com.between_freedom_and_space.mono_backend.access.service.models.CreateRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class CreateRuleRequestToModelMapper: ModelMapper<CreateRuleRequest, CreateRuleModel> {

    override fun map(original: CreateRuleRequest): CreateRuleModel {
        return CreateRuleModel(
            pathPattern = original.pathPattern,
            isActive = original.isActive
        )
    }
}