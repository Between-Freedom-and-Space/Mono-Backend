package com.between_freedom_and_space.mono_backend.access.service.models

data class UpdateRoleRuleModel(

    val newRuleId: Long?,

    val newRoleAlias: String?,

    val newIsActive: Boolean?
)
