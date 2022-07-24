package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserRole
import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserRolesTable
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessedRole
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessRulesTable
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedRolesTable
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedRolesRepository
import com.between_freedom_and_space.mono_backend.access.repository.exceptions.RuleAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRoleRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.service.exception.RoleNotFoundException
import com.between_freedom_and_space.mono_backend.access.service.exception.RuleNotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select

class CommonAccessedRolesRepositoryImpl: CommonAccessedRolesRepository {

    override fun getAllRoleRules(role: Role, includeNotActive: Boolean): List<AccessRule> {
        val targetRoleQuery = UserRolesTable.select {
            UserRolesTable.roleAlias eq role
        }.firstOrNull() ?: throw RoleNotFoundException("Role with alias: $role not found")
        val userRole = UserRole.wrapRow(targetRoleQuery)
        val roleRuleQuery = AccessedRolesTable.select {
            AccessedRolesTable.role eq userRole.id
        }.run {
            if (!includeNotActive) {
                andWhere { AccessedRolesTable.isActive eq true }
            } else { this }
        }

        val accessedRoles = AccessedRole.wrapRows(roleRuleQuery).map { it.accessRule }
        val rulesQuery = AccessRulesTable.select {
            AccessRulesTable.id inList accessedRoles and AccessRulesTable.isActive
        }
        val result = AccessRule.wrapRows(rulesQuery)
        return result.toList()
    }

    override fun getRoleRuleById(roleRuleId: Long, includeNotActive: Boolean): AccessedRole? {
        val rule = AccessedRole.find {
            AccessedRolesTable.id eq roleRuleId
        }.firstOrNull()
        if (!includeNotActive) {
            return if (rule?.isActive == false) null else rule
        }
        return rule
    }

    override fun getRoleRuleByAlias(alias: String, includeNotActive: Boolean): AccessedRole? {
        val role = UserRole.find {
            UserRolesTable.roleAlias eq Role.valueOf(alias)
        }.firstOrNull() ?: return null
        val roleId = role.id
        val rule = AccessedRole.find {
            AccessedRolesTable.role eq roleId
        }.firstOrNull()
        if (!includeNotActive) {
            return if (rule?.isActive == false) null else rule
        }
        return rule
    }

    override fun createRoleRule(authorId: EntityID<Long>, model: CreateRoleRuleEntityModel): AccessedRole {
        val targetRule = AccessRule.findById(model.ruleId)
            ?: throw RuleNotFoundException("Rule with id: ${model.ruleId} not found")
        val targetRoleQuery = UserRolesTable.select {
            UserRolesTable.roleAlias eq model.role
        }.firstOrNull() ?: throw RoleNotFoundException("Role with alias: ${model.role} not found")
        val targetRole = UserRole.wrapRow(targetRoleQuery)

        return AccessedRole.new {
            role = targetRole.id
            ruleGivenBy = authorId
            accessRule = targetRule.id
            isActive = model.isActive
        }
    }

    override fun deleteRoleRule(authorId: EntityID<Long>, roleRuleId: Long): AccessedRole? {
        val rule = AccessedRole.findById(roleRuleId) ?: return null
        if (!rule.isActive) {
            throw RuleAlreadyDeletedException("Role rule with id: $roleRuleId already deleted")
        }
        rule.isActive = false
        rule.ruleGivenBy = authorId
        return rule
    }

    override fun saveRoleRule(roleRule: AccessedRole): AccessedRole {
        roleRule.flush()
        return roleRule
    }
}