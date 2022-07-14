package com.between_freedom_and_space.mono_backend.access.service.impl

import com.between_freedom_and_space.mono_backend.access.service.InteractionAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.models.*

class InteractionAccessRulesServiceImpl: InteractionAccessRulesService {

    override fun createAccessRule(authorId: Long, model: CreateRuleModel): BaseAccessRuleModel {
        TODO("Not yet implemented")
    }

    override fun createUserAccessRule(authorId: Long, model: CreateUserRuleModel): BaseAccessRuleModel {
        TODO("Not yet implemented")
    }

    override fun createRoleAccessRule(authorId: Long, model: CreateRoleRuleModel): BaseAccessRuleModel {
        TODO("Not yet implemented")
    }

    override fun updateAccessRule(authorId: Long, model: UpdateRuleModel): BaseAccessRuleModel {
        TODO("Not yet implemented")
    }

    override fun updateUserAccessRule(authorId: Long, model: UpdateUserRuleModel): BaseAccessRuleModel {
        TODO("Not yet implemented")
    }

    override fun updateRoleAccessRule(authorId: Long, model: UpdateRoleRuleModel): BaseAccessRuleModel {
        TODO("Not yet implemented")
    }

    override fun deleteAccessRule(authorId: Long, ruleId: Long): BaseAccessRuleModel {
        TODO("Not yet implemented")
    }

    override fun deleteUserAccessRule(authorId: Long, userRuleId: Long): BaseAccessRuleModel {
        TODO("Not yet implemented")
    }

    override fun deleteRoleAccessRule(authorId: Long, roleRuleId: Long): BaseAccessRuleModel {
        TODO("Not yet implemented")
    }
}