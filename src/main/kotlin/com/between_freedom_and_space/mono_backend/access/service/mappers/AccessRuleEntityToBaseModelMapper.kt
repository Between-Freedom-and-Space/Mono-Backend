package com.between_freedom_and_space.mono_backend.access.service.mappers

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.service.models.BaseAccessRuleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class AccessRuleEntityToBaseModelMapper: ModelMapper<AccessRule, BaseAccessRuleModel> {

    override fun map(original: AccessRule): BaseAccessRuleModel {
        return BaseAccessRuleModel(
            id = original.id.value,
            pathPattern = original.pathPattern,
            modifiedByUserId = original.lastModifiedBy?.value,
            isActive = original.isActive,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}