package com.between_freedom_and_space.mono_backend.access.api.mappers

import com.between_freedom_and_space.mono_backend.access.api.models.UpdateRuleRequest
import com.between_freedom_and_space.mono_backend.access.service.models.UpdateRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class UpdateRuleRequestToModelMapper: ModelMapper<UpdateRuleRequest, UpdateRuleModel> {

    override fun map(original: UpdateRuleRequest): UpdateRuleModel {
        return UpdateRuleModel(
            newPathPattern = original.newRulePathPattern,
            newIsActive = original.newIsActive
        )
    }
}