package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID

interface CommonAccessRuleRepository {

    fun getAllAccessRules(pageNumber: Int, pageSize: Int, includeNotActive: Boolean = false): List<AccessRule>

    fun getAccessRuleById(id: Long, includeNotActive: Boolean = false): AccessRule?

    fun createAccessRule(authorId: EntityID<Long>, model: CreateRuleEntityModel): AccessRule

    fun deleteAccessRule(authorId: EntityID<Long>, ruleId: Long): AccessRule?

    fun saveAccessRule(rule: AccessRule): AccessRule
}