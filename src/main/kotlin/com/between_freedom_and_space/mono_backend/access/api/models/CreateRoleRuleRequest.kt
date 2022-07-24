package com.between_freedom_and_space.mono_backend.access.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Serializable
data class CreateRoleRuleRequest(

    @SerialName("rule_id")
    @get:Min(value = 0, message = "Rule id can't be less than zero")
    val ruleId: Long,

    @SerialName("role_alias")
    @get:NotBlank(message = "Role alias can't be blank")
    val roleAlias: String,

    @SerialName("is_active")
    val isActive: Boolean,
)
