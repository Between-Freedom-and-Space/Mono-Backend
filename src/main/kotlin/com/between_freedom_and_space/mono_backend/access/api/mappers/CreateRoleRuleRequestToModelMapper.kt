package com.between_freedom_and_space.mono_backend.access.api.mappers

import com.between_freedom_and_space.mono_backend.access.api.models.CreateRoleRuleRequest
import com.between_freedom_and_space.mono_backend.access.service.models.CreateRoleRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class CreateRoleRuleRequestToModelMapper: ModelMapper<CreateRoleRuleRequest, CreateRoleRuleModel> {

    override fun map(original: CreateRoleRuleRequest): CreateRoleRuleModel {
        return CreateRoleRuleModel(
            ruleId = original.ruleId,
            roleAlias = original.roleAlias,
            isActive = original.isActive,
        )
    }
}