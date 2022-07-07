package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedRolesRepository

class CommonAccessedRolesRepositoryImpl: CommonAccessedRolesRepository {

    override fun getAllRoleRules(role: Role): List<AccessRule> {
        TODO("Not yet implemented")
    }
}