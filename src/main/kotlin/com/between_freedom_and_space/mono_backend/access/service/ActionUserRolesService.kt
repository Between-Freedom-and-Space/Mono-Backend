package com.between_freedom_and_space.mono_backend.access.service

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel

interface ActionUserRolesService {

    fun changeUserRole(nickname: String, newRole: Role): BaseUserRoleModel
}