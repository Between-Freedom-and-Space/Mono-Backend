package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileIconModel(

    @SerialName("id")
    val id: Long,

    @SerialName("icon_base64")
    val iconBase64: String,

    @SerialName("profile_id")
    val profileId: Long,

    @SerialName("created_date")
    val createdDate: LocalDateTime,

    @SerialName("updated_date")
    val updatedDate: LocalDateTime,
)
