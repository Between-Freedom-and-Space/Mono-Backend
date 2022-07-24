package com.between_freedom_and_space.mono_backend.access.api.mappers

import com.between_freedom_and_space.mono_backend.access.api.models.AccessRuleModel
import com.between_freedom_and_space.mono_backend.access.service.models.BaseAccessRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class BaseAccessRuleModelToModelMapper: ModelMapper<BaseAccessRuleModel, AccessRuleModel> {

    override fun map(original: BaseAccessRuleModel): AccessRuleModel {
        return AccessRuleModel(
            id = original.id,
            pathPattern = original.pathPattern,
            lastModifiedByUserId = original.modifiedByUserId,
            isActive = original.isActive,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}