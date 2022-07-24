package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessedRole
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRoleRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID

interface CommonAccessedRolesRepository {

    fun getAllRoleRules(role: Role, includeNotActive: Boolean = false): List<AccessRule>

    fun getRoleRuleById(roleRuleId: Long, includeNotActive: Boolean = false): AccessedRole?

    fun getRoleRuleByAlias(alias: String, includeNotActive: Boolean = false): AccessedRole?

    fun createRoleRule(authorId: EntityID<Long>, model: CreateRoleRuleEntityModel): AccessedRole

    fun deleteRoleRule(authorId: EntityID<Long>, roleRuleId: Long): AccessedRole?

    fun saveRoleRule(roleRule: AccessedRole): AccessedRole
}