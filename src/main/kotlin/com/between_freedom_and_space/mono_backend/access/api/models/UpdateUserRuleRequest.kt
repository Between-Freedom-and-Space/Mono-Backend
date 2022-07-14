package com.between_freedom_and_space.mono_backend.access.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRuleRequest(

    @SerialName("new_user_id")
    val newUserId: Long? = null,

    @SerialName("new_rule_id")
    val newRuleId: Long? = null,

    @SerialName("new_is_active")
    val newIsActive: Boolean? = null,
)
