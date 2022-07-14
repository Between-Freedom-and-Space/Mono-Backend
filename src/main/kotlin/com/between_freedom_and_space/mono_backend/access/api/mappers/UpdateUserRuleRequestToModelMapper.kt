package com.between_freedom_and_space.mono_backend.access.api.mappers

import com.between_freedom_and_space.mono_backend.access.api.models.UpdateUserRuleRequest
import com.between_freedom_and_space.mono_backend.access.service.models.UpdateUserRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class UpdateUserRuleRequestToModelMapper: ModelMapper<UpdateUserRuleRequest, UpdateUserRuleModel> {

    override fun map(original: UpdateUserRuleRequest): UpdateUserRuleModel {
        return UpdateUserRuleModel(
            newRuleId = original.newRuleId,
            newUserId = original.newUserId,
            newIsActive = original.newIsActive,
        )
    }
}