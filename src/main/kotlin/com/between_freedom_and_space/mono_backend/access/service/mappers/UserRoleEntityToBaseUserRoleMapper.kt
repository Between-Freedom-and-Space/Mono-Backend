package com.between_freedom_and_space.mono_backend.access.service.mappers

import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserRole
import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class UserRoleEntityToBaseUserRoleMapper: ModelMapper<UserRole, BaseUserRoleModel> {

    override fun map(original: UserRole): BaseUserRoleModel {
        return BaseUserRoleModel(
            id = original.id.value,
            role = original.roleAlias,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate
        )
    }
}