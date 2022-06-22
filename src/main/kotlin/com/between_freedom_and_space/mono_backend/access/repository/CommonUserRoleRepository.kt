package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserRole

interface CommonUserRoleRepository {

    fun getAllUserRoles(): List<UserRole>

    fun getUserRoleById(roleId: Long): UserRole?
}