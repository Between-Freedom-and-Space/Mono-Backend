package com.between_freedom_and_space.mono_backend.access.api.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRoleModel(

    @SerialName("role_id")
    val id: Long,

    @SerialName("role_alias")
    val roleAlias: String,

    @SerialName("created_date")
    val createdDate: LocalDateTime,

    @SerialName("updated_date")
    val updatedDate: LocalDateTime,
)
