package com.between_freedom_and_space.mono_backend.access.api.mappers

import com.between_freedom_and_space.mono_backend.access.api.models.CreateUserRuleRequest
import com.between_freedom_and_space.mono_backend.access.service.models.CreateUserRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class CreateUserRuleRequestToModelMapper: ModelMapper<CreateUserRuleRequest, CreateUserRuleModel> {

    override fun map(original: CreateUserRuleRequest): CreateUserRuleModel {
        return CreateUserRuleModel(
            ruleId = original.ruleId,
            userId = original.userId,
            isActive = original.isActive,
        )
    }
}