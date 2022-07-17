package com.between_freedom_and_space.mono_backend.access.service.impl

import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessRuleRepository
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedRolesRepository
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedUsersRepository
import com.between_freedom_and_space.mono_backend.access.service.InformationAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.exception.RuleNotFoundException
import com.between_freedom_and_space.mono_backend.access.service.models.BaseAccessRuleModel
import com.between_freedom_and_space.mono_backend.access.service.models.RuleCheckResult
import com.between_freedom_and_space.mono_backend.access.service.models.RuleCheckResult.CheckResult
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.components.UserProfileIdProvider
import org.jetbrains.exposed.sql.transactions.transaction

@Suppress("DuplicatedCode")
class InformationAccessRulesServiceImpl(
    private val accessRuleRepository: CommonAccessRuleRepository,
    private val ruleToRoleRepository: CommonAccessedRolesRepository,
    private val ruleToUserRepository: CommonAccessedUsersRepository,
    private val pathPatternMatcher: PathPatternMatcher,
    private val userIdProvider: UserProfileIdProvider,
    private val baseMapper: ModelMapper<AccessRule, BaseAccessRuleModel>
): InformationAccessRulesService {

    override fun getAllAccessRules(pageNumber: Int, pageSize: Int): List<BaseAccessRuleModel> {
        val rules = transaction {
            accessRuleRepository.getAllAccessRules(pageNumber, pageSize)
        }
        return rules.map { baseMapper.map(it) }
    }

    override fun getAccessRuleById(id: Long): BaseAccessRuleModel {
        val rule = transaction {
            accessRuleRepository.getAccessRuleById(id)
                ?: throw RuleNotFoundException("Access rule with id: $id not found")
        }
        return baseMapper.map(rule)
    }

    override fun getAllUserRules(nickname: String): List<BaseAccessRuleModel> {
        val rules = transaction {
            val userId = userIdProvider.getUser(nickname)
            ruleToUserRepository.getAllUserRules(userId)
        }
        return rules.map { baseMapper.map(it) }
    }

    override fun getAllRoleRules(roleAlias: String): List<BaseAccessRuleModel> {
        val rules = transaction {
            val role = Role.valueOf(roleAlias)
            ruleToRoleRepository.getAllRoleRules(role)
        }
        return rules.map { baseMapper.map(it) }
    }

    override fun checkRoleAccessToPath(role: Role, rawPath: String): RuleCheckResult {
        val foundedRule = transaction {
            val rules = ruleToRoleRepository.getAllRoleRules(role)
            rules.findMatchesRule(rawPath)
        }

        return if (foundedRule != null) {
            val rule = baseMapper.map(foundedRule)
            RuleCheckResult(CheckResult.HAS_ACCESS, rule = rule)
        } else {
            RuleCheckResult(CheckResult.NO_ACCESS, rule = null)
        }
    }

    override fun checkUserAccessToPath(userId: Long, rawPath: String): RuleCheckResult {
        val foundedRule = transaction {
            val userEntityId = userIdProvider.getUser(userId)
            val rules = ruleToUserRepository.getAllUserRules(userEntityId)
            rules.findMatchesRule(rawPath)
        }

        return if (foundedRule != null) {
            val rule = baseMapper.map(foundedRule)
            RuleCheckResult(CheckResult.HAS_ACCESS, rule = rule)
        } else {
            RuleCheckResult(CheckResult.NO_ACCESS, rule = null)
        }
    }

    override fun checkUserAccessToPath(nickname: String, rawPath: String): RuleCheckResult {
        val foundedRule = transaction {
            val userId = userIdProvider.getUser(nickname)
            val rules = ruleToUserRepository.getAllUserRules(userId)
            rules.findMatchesRule(rawPath)
        }

        return if (foundedRule != null) {
            val rule = baseMapper.map(foundedRule)
            RuleCheckResult(CheckResult.HAS_ACCESS, rule = rule)
        } else {
            RuleCheckResult(CheckResult.NO_ACCESS, rule = null)
        }
    }

    private fun List<AccessRule>.findMatchesRule(rawPath: String): AccessRule? {
        return find { rule ->
            pathPatternMatcher.pathMatchesPattern(rule.pathPattern, rawPath)
        }
    }
}