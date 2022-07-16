package com.between_freedom_and_space.mono_backend.access.service.mappers

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRoleRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.service.models.CreateRoleRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class CreateRoleRuleModelToEntityMapper: ModelMapper<CreateRoleRuleModel, CreateRoleRuleEntityModel> {

    override fun map(original: CreateRoleRuleModel): CreateRoleRuleEntityModel {
        return CreateRoleRuleEntityModel(
            ruleId = original.ruleId,
            role = Role.valueOf(original.roleAlias),
            isActive = original.isActive,
        )
    }
}