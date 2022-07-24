package com.between_freedom_and_space.mono_backend.access.service.models

data class CreateRoleRuleModel(

    val ruleId: Long,

    val roleAlias: String,

    val isActive: Boolean,
)
