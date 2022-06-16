package com.between_freedom_and_space.mono_backend.access.api.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessRuleModel(

    @SerialName("access_rule_id")
    val id: Long,

    @SerialName("path_pattern")
    val pathPattern: String,

    @SerialName("last_modified_by_user_id")
    val lastModifiedByUserId: Long?,

    @SerialName("is_active")
    val isActive: Boolean,

    @SerialName("created_date")
    val createdDate: LocalDateTime,

    @SerialName("updated_date")
    val updatedDate: LocalDateTime,
)
