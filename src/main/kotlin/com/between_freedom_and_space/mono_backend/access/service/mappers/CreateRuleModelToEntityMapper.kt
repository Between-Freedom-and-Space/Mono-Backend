package com.between_freedom_and_space.mono_backend.access.service.mappers

import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.service.models.CreateRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class CreateRuleModelToEntityMapper: ModelMapper<CreateRuleModel, CreateRuleEntityModel> {

    override fun map(original: CreateRuleModel): CreateRuleEntityModel {
        return CreateRuleEntityModel(
            pathPattern = original.pathPattern,
            isActive = original.isActive,
        )
    }
}