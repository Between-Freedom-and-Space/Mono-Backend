package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessRuleRepository
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID

class CommonAccessRuleRepositoryImpl: CommonAccessRuleRepository {

    override fun getAllAccessRules(pageNumber: Int, pageSize: Int): List<AccessRule> {
        TODO("Not yet implemented")
    }

    override fun getAccessRuleById(id: Long): AccessRule? {
        TODO("Not yet implemented")
    }

    override fun createAccessRule(authorId: EntityID<Long>, model: CreateRuleEntityModel): AccessRule {
        TODO("Not yet implemented")
    }

    override fun deleteAccessRule(authorId: EntityID<Long>, ruleId: Long): AccessRule? {
        TODO("Not yet implemented")
    }
}