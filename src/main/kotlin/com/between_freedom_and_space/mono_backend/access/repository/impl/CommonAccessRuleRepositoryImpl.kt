package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessRulesTable
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessRuleRepository
import com.between_freedom_and_space.mono_backend.access.repository.exceptions.RuleAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select

class CommonAccessRuleRepositoryImpl: CommonAccessRuleRepository {

    override fun getAllAccessRules(pageNumber: Int, pageSize: Int): List<AccessRule> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = AccessRulesTable
            .select {
                AccessRulesTable.isActive eq true
            }
            .limit(pageSize, offset)
        val result = AccessRule.wrapRows(query)
        return result.toList()
    }

    override fun getAccessRuleById(id: Long): AccessRule? {
        return AccessRule.find {
            AccessRulesTable.id eq id and AccessRulesTable.isActive
        }.firstOrNull()
    }

    override fun createAccessRule(authorId: EntityID<Long>, model: CreateRuleEntityModel): AccessRule {
        return AccessRule.new {
            pathPattern = model.pathPattern
            lastModifiedBy = authorId
            isActive = model.isActive
        }
    }

    override fun deleteAccessRule(authorId: EntityID<Long>, ruleId: Long): AccessRule? {
        val rule = AccessRule.findById(ruleId) ?: return null
        if (!rule.isActive) {
            throw RuleAlreadyDeletedException("Rule with id: $rule already deleted")
        }
        rule.isActive = false
        rule.lastModifiedBy = authorId
        return rule
    }

    override fun saveAccessRule(rule: AccessRule): AccessRule {
        rule.flush()
        return rule
    }
}