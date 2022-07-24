package com.between_freedom_and_space.mono_backend.access.service.models

data class CreateUserRuleModel(

    val ruleId: Long,

    val userId: Long,

    val isActive: Boolean,
)
