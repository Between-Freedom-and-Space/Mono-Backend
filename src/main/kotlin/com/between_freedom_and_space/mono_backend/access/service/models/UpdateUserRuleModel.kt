package com.between_freedom_and_space.mono_backend.access.service.models

data class UpdateUserRuleModel(

    val newRuleId: Long?,

    val newUserId: Long?,

    val newIsActive: Boolean,
)
