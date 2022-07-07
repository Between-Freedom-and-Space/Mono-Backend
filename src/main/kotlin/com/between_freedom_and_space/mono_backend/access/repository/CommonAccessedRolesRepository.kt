package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule

interface CommonAccessedRolesRepository {

    fun getAllRoleRules(role: Role): List<AccessRule>
}