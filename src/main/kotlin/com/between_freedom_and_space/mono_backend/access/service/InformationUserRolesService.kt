package com.between_freedom_and_space.mono_backend.access.service

import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel

interface InformationUserRolesService {

    fun getAllRoles(): Collection<BaseUserRoleModel>

    fun getRoleById(roleId: Long): BaseUserRoleModel

    fun getUserRole(userId: Long): BaseUserRoleModel

    fun getUserRole(nickname: String): BaseUserRoleModel
}