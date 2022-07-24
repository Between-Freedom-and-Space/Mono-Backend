package com.between_freedom_and_space.mono_backend.access.api.mappers

import com.between_freedom_and_space.mono_backend.access.api.models.UserRoleModel
import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class BaseUserRoleModelToModelMapper: ModelMapper<BaseUserRoleModel, UserRoleModel> {

    override fun map(original: BaseUserRoleModel): UserRoleModel {
        return UserRoleModel(
            id = original.id,
            roleAlias = original.role.name,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}