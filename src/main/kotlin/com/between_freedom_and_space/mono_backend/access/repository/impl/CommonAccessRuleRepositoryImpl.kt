package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessRulesTable
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessRuleRepository
import com.between_freedom_and_space.mono_backend.access.repository.exceptions.RuleAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class CommonAccessRuleRepositoryImpl: CommonAccessRuleRepository {

    override fun getAllAccessRules(pageNumber: Int, pageSize: Int, includeNotActive: Boolean): List<AccessRule> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = AccessRulesTable
            .selectAll()
            .run {
                if (!includeNotActive) {
                    andWhere { AccessRulesTable.isActive eq true }
                } else { this }
            }
            .limit(pageSize, offset)
        val result = AccessRule.wrapRows(query)
        return result.toList()
    }

    override fun getAccessRuleById(id: Long, includeNotActive: Boolean): AccessRule? {
        val rule = AccessRule.find {
            AccessRulesTable.id eq id
        }.firstOrNull()
        if (!includeNotActive) {
            return if (rule?.isActive == false) null else rule
        }
        return rule
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