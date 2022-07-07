package com.between_freedom_and_space.mono_backend.access.service

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.service.models.BaseAccessRuleModel
import com.between_freedom_and_space.mono_backend.access.service.models.RuleCheckResult

interface InformationAccessRulesService {

    fun getAllAccessRules(pageNumber: Int, pageSize: Int): List<BaseAccessRuleModel>

    fun getAccessRuleById(id: Long): BaseAccessRuleModel

    fun getAllUserRules(nickname: String): List<BaseAccessRuleModel>

    fun getAllRoleRules(roleAlias: String): List<BaseAccessRuleModel>

    fun checkRoleAccessToPath(role: Role, rawPath: String): RuleCheckResult

    fun checkUserAccessToPath(userId: Long, rawPath: String): RuleCheckResult
}