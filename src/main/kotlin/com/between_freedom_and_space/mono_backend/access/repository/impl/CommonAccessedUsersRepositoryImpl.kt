package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessedUser
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessRulesTable
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedUsersTable
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedUsersRepository
import com.between_freedom_and_space.mono_backend.access.repository.exceptions.RuleAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateUserRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.service.exception.RuleNotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select

class CommonAccessedUsersRepositoryImpl: CommonAccessedUsersRepository {

    override fun getAllUserRules(userId: EntityID<Long>, includeNotActive: Boolean): List<AccessRule> {
        val userRuleQuery = AccessedUsersTable.select {
            AccessedUsersTable.user eq userId
        }.run {
            if (!includeNotActive) {
                andWhere { AccessedUsersTable.isActive eq true }
            } else { this }
        }
        val userRules = AccessedUser.wrapRows(userRuleQuery)
        val accessRulesId = userRules.map { it.accessRule }
        val ruleQuery = AccessRulesTable.select {
            AccessRulesTable.id inList accessRulesId
        }.run {
            if (!includeNotActive) {
                andWhere { AccessRulesTable.isActive eq true }
            } else { this }
        }
        val result = AccessRule.wrapRows(ruleQuery)
        return result.toList()
    }

    override fun getUserRuleById(userRuleId: Long, includeNotActive: Boolean): AccessedUser? {
        val rule = AccessedUser.find {
            AccessedUsersTable.id eq userRuleId
        }.firstOrNull()
        if (!includeNotActive) {
            return if (rule?.isActive == false) null else rule
        }
        return rule
    }

    override fun createUserRule(
        authorId: EntityID<Long>, userId: EntityID<Long>, model: CreateUserRuleEntityModel
    ): AccessedUser {
        val rule = AccessRule.findById(model.ruleId)
            ?: throw RuleNotFoundException("Rule with id: ${model.ruleId} not found")

        return AccessedUser.new {
            user = userId
            ruleGivenBy = authorId
            accessRule = rule.id
            isActive = model.isActive
        }
    }

    override fun deleteUserRule(authorId: EntityID<Long>, userRuleId: Long): AccessedUser? {
        val userRule = AccessedUser.findById(userRuleId) ?: return null
        if (!userRule.isActive) {
            throw RuleAlreadyDeletedException("User Rule with id: $userRuleId already deleted")
        }
        userRule.isActive = false
        userRule.ruleGivenBy = authorId
        return userRule
    }

    override fun saveUserRule(userRule: AccessedUser): AccessedUser {
        userRule.flush()
        return userRule
    }
}