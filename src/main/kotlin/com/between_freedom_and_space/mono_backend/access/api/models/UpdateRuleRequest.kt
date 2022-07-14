package com.between_freedom_and_space.mono_backend.access.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateRuleRequest(

    @SerialName("new_rule_path_pattern")
    val newRulePathPattern: String? = null,

    @SerialName("new_is_active")
    val newIsActive: Boolean? = null,
)
