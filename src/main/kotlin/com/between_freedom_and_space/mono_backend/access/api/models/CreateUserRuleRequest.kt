package com.between_freedom_and_space.mono_backend.access.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Min

@Serializable
data class CreateUserRuleRequest(

    @SerialName("rule_id")
    @get:Min(value = 0, message = "Rule id can't be less than zero")
    val ruleId: Long,

    @SerialName("user_id")
    @get:Min(value = 0, message = "User id can't be less than zero")
    val userId: Long,

    @SerialName("is_active")
    val isActive: Boolean,
)
