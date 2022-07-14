package com.between_freedom_and_space.mono_backend.access.api.mappers

import com.between_freedom_and_space.mono_backend.access.api.models.UpdateRoleRuleRequest
import com.between_freedom_and_space.mono_backend.access.service.models.UpdateRoleRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class UpdateRoleRuleRequestToModelMapper: ModelMapper<UpdateRoleRuleRequest, UpdateRoleRuleModel> {

    override fun map(original: UpdateRoleRuleRequest): UpdateRoleRuleModel {
        return UpdateRoleRuleModel(
            newRuleId = original.newRuleId,
            newRoleAlias = original.newRoleAlias,
            newIsActive = original.newIsActive,
        )
    }
}