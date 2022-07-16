package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessedRole
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRoleRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID

interface CommonAccessedRolesRepository {

    fun getAllRoleRules(role: Role): List<AccessRule>

    fun createRoleRule(authorId: EntityID<Long>, model: CreateRoleRuleEntityModel): AccessedRole

    fun deleteRoleRule(authorId: EntityID<Long>, roleRuleId: Long): AccessedRole?
}