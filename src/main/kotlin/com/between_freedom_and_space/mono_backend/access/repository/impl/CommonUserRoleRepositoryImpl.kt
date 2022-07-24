package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserRole
import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserRolesTable
import com.between_freedom_and_space.mono_backend.access.repository.CommonUserRoleRepository

class CommonUserRoleRepositoryImpl: CommonUserRoleRepository {

    override fun getAllUserRoles(): List<UserRole> {
        return UserRole.all().toList()
    }

    override fun getUserRoleById(roleId: Long): UserRole? {
        return UserRole.findById(roleId)
    }

    override fun getUserRoleByAlias(roleAlias: String): UserRole? {
        return UserRole.find {
            UserRolesTable.roleAlias eq Role.valueOf(roleAlias)
        }.firstOrNull()
    }
}