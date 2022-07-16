package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessedRole
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedRolesRepository
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRoleRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID

class CommonAccessedRolesRepositoryImpl: CommonAccessedRolesRepository {

    override fun getAllRoleRules(role: Role): List<AccessRule> {
        TODO("Not yet implemented")
    }

    override fun createRoleRule(authorId: EntityID<Long>, model: CreateRoleRuleEntityModel): AccessedRole {
        TODO("Not yet implemented")
    }

    override fun deleteRoleRule(authorId: EntityID<Long>, roleRuleId: Long): AccessedRole? {
        TODO("Not yet implemented")
    }
}