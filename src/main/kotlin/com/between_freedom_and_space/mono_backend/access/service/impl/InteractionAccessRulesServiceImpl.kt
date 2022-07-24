package com.between_freedom_and_space.mono_backend.access.service.impl

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessRuleRepository
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedRolesRepository
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedUsersRepository
import com.between_freedom_and_space.mono_backend.access.repository.CommonUserRoleRepository
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRoleRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateUserRuleEntityModel
import com.between_freedom_and_space.mono_backend.access.service.InteractionAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.exception.RoleNotFoundException
import com.between_freedom_and_space.mono_backend.access.service.exception.RuleNotFoundException
import com.between_freedom_and_space.mono_backend.access.service.models.*
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.components.UserProfileIdProvider
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionAccessRulesServiceImpl(
    private val roleRepository: CommonUserRoleRepository,
    private val ruleRepository: CommonAccessRuleRepository,
    private val ruleToUserRepository: CommonAccessedUsersRepository,
    private val ruleToRoleRepository: CommonAccessedRolesRepository,
    private val userIdProvider: UserProfileIdProvider,
    private val entityMapper: ModelMapper<AccessRule, BaseAccessRuleModel>,
    private val createRuleMapper: ModelMapper<CreateRuleModel, CreateRuleEntityModel>,
    private val createRoleRuleMapper: ModelMapper<CreateRoleRuleModel, CreateRoleRuleEntityModel>,
    private val createUserRuleMapper: ModelMapper<CreateUserRuleModel, CreateUserRuleEntityModel>,
): InteractionAccessRulesService {

    override fun createAccessRule(authorId: Long, model: CreateRuleModel): BaseAccessRuleModel {
        val rule = transaction {
            val authorEntityId = userIdProvider.getUser(authorId)
            val entityModel = createRuleMapper.map(model)
            ruleRepository.createAccessRule(authorEntityId, entityModel)
        }

        return entityMapper.map(rule)
    }

    override fun createUserAccessRule(authorId: Long, model: CreateUserRuleModel): BaseAccessRuleModel {
        val rule = transaction {
            val authorEntityId = userIdProvider.getUser(authorId)
            val userId = userIdProvider.getUser(model.userId)
            val entityModel = createUserRuleMapper.map(model)
            val accessedUser = ruleToUserRepository.createUserRule(authorEntityId, userId, entityModel)
            val ruleId = accessedUser.accessRule.value

            ruleRepository.getAccessRuleById(ruleId)
                ?: throw RuleNotFoundException("Rule with id: $ruleId not found")
        }

        return entityMapper.map(rule)
    }

    override fun createRoleAccessRule(authorId: Long, model: CreateRoleRuleModel): BaseAccessRuleModel {
        val rule = transaction {
            val authorEntityId = userIdProvider.getUser(authorId)
            val entityModel = createRoleRuleMapper.map(model)
            val accessedRole = ruleToRoleRepository.createRoleRule(authorEntityId, entityModel)
            val ruleId = accessedRole.accessRule.value

            ruleRepository.getAccessRuleById(ruleId)
                ?: throw RuleNotFoundException("Rule with id: $ruleId not found")
        }

        return entityMapper.map(rule)
    }

    override fun updateAccessRule(
        ruleId: Long, authorId: Long, model: UpdateRuleModel
    ): BaseAccessRuleModel {
        val rule = transaction {
            val targetRule = getRuleOrThrow(ruleId, true)
            val authorEntityId = userIdProvider.getUser(authorId)

            model.newIsActive?.let { targetRule.isActive = it }
            model.newPathPattern?.let { targetRule.pathPattern = it }
            targetRule.lastModifiedBy = authorEntityId

            ruleRepository.saveAccessRule(targetRule)
        }

        return entityMapper.map(rule)
    }

    override fun updateUserAccessRule(
        userRuleId: Long, authorId: Long, model: UpdateUserRuleModel
    ): BaseAccessRuleModel {
        val rule = transaction {
            val targetRule = ruleToUserRepository.getUserRuleById(userRuleId, includeNotActive = true)
                ?: throw RuleNotFoundException("User rule with id: $userRuleId not found")
            val authorEntityId = userIdProvider.getUser(authorId)

            model.newIsActive?.let { targetRule.isActive = it }
            model.newRuleId?.let { newRuleId ->
                val newRule = getRuleOrThrow(newRuleId)
                targetRule.accessRule = newRule.id
            }
            model.newUserId?.let { newUserId ->
                val newUser = userIdProvider.getUser(newUserId)
                targetRule.user = newUser
            }
            targetRule.ruleGivenBy = authorEntityId

            val savedUserRule = ruleToUserRepository.saveUserRule(targetRule)
            val savedRuleId = savedUserRule.accessRule.value

            getRuleOrThrow(savedRuleId)
        }

        return entityMapper.map(rule)
    }

    override fun updateRoleAccessRule(
        roleRuleId: Long, authorId: Long, model: UpdateRoleRuleModel
    ): BaseAccessRuleModel {
        val rule = transaction {
            val targetRule = ruleToRoleRepository.getRoleRuleById(roleRuleId, includeNotActive = true)
                ?: throw RuleNotFoundException("Role rule with id: $roleRuleId not found")
            val authorEntityId = userIdProvider.getUser(authorId)

            model.newIsActive?.let { targetRule.isActive = it }
            model.newRuleId?.let { newRuleId ->
                val rule = getRuleOrThrow(newRuleId)
                targetRule.accessRule = rule.id
            }
            model.newRoleAlias?.let { newRoleAlias ->
                val role = roleRepository.getUserRoleByAlias(newRoleAlias)
                    ?: throw RoleNotFoundException("Role with alias: $newRoleAlias not found")
                targetRule.role = role.id
            }
            targetRule.ruleGivenBy = authorEntityId

            val savedRoleRule = ruleToRoleRepository.saveRoleRule(targetRule)
            val savedRuleId = savedRoleRule.accessRule.value

            getRuleOrThrow(savedRuleId)
        }

        return entityMapper.map(rule)
    }

    override fun deleteAccessRule(authorId: Long, ruleId: Long): BaseAccessRuleModel {
        val rule = transaction {
            val authorEntityId = userIdProvider.getUser(authorId)
            ruleRepository.deleteAccessRule(authorEntityId, ruleId)
                ?: throw RuleNotFoundException("Rule with id: $ruleId not found")
        }
        return entityMapper.map(rule)
    }

    override fun deleteUserAccessRule(authorId: Long, userRuleId: Long): BaseAccessRuleModel {
        val rule = transaction {
            val authorEntityId = userIdProvider.getUser(authorId)
            val deletedUserRule = ruleToUserRepository.deleteUserRule(authorEntityId, userRuleId)
                ?: throw RuleNotFoundException("User Rule with id: $userRuleId not found")
            val ruleId = deletedUserRule.accessRule.value

            getRuleOrThrow(ruleId)
        }
        return entityMapper.map(rule)
    }

    override fun deleteRoleAccessRule(authorId: Long, roleRuleId: Long): BaseAccessRuleModel {
        val rule = transaction {
            val authorEntityId = userIdProvider.getUser(authorId)
            val deletedRoleRule = ruleToRoleRepository.deleteRoleRule(authorEntityId, roleRuleId)
                ?: throw RuleNotFoundException("Role Rule with id: $roleRuleId not found")
            val ruleId = deletedRoleRule.accessRule.value

            getRuleOrThrow(ruleId)
        }
        return entityMapper.map(rule)
    }

    private fun getRuleOrThrow(ruleId: Long, includeNotActive: Boolean = false): AccessRule {
        return ruleRepository.getAccessRuleById(ruleId, includeNotActive)
            ?: throw RuleNotFoundException("Rule with id: $ruleId not found")
    }
}