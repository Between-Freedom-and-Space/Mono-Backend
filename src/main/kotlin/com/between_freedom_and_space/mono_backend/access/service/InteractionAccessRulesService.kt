package com.between_freedom_and_space.mono_backend.access.service

import com.between_freedom_and_space.mono_backend.access.service.models.*

interface InteractionAccessRulesService {

    fun createAccessRule(authorId: Long, model: CreateRuleModel): BaseAccessRuleModel

    fun createUserAccessRule(authorId: Long, model: CreateUserRuleModel): BaseAccessRuleModel

    fun createRoleAccessRule(authorId: Long, model: CreateRoleRuleModel): BaseAccessRuleModel

    fun updateAccessRule(authorId: Long, model: UpdateRuleModel): BaseAccessRuleModel

    fun updateUserAccessRule(authorId: Long, model: UpdateUserRuleModel): BaseAccessRuleModel

    fun updateRoleAccessRule(authorId: Long, model: UpdateRoleRuleModel): BaseAccessRuleModel

    fun deleteAccessRule(authorId: Long, ruleId: Long): BaseAccessRuleModel

    fun deleteUserAccessRule(authorId: Long, userRuleId: Long): BaseAccessRuleModel

    fun deleteRoleAccessRule(authorId: Long, roleRuleId: Long): BaseAccessRuleModel
}