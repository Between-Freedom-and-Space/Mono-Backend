package com.between_freedom_and_space.mono_backend.access.service.mappers

import com.between_freedom_and_space.mono_backend.access.repository.models.CreateUserRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.service.models.CreateUserRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class CreateUserRuleModelToEntityMapper: ModelMapper<CreateUserRuleModel, CreateUserRuleEntityModel> {

    override fun map(original: CreateUserRuleModel): CreateUserRuleEntityModel {
        return CreateUserRuleEntityModel(
            ruleId = original.ruleId,
            isActive = original.isActive,
        )
    }
}