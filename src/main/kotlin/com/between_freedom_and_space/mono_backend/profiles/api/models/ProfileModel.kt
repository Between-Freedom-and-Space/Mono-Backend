package com.between_freedom_and_space.mono_backend.profiles.api.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileModel(

    @SerialName("profile_id")
    val id: Long,

    @SerialName("nickname")
    val nickName: String,

    @SerialName("name_alias")
    val nameAlias: String,

    @SerialName("description")
    val description: String?,

    @SerialName("location")
    val location: String?,

    @SerialName("created_date")
    val createdDate: LocalDateTime,

    @SerialName("updated_date")
    val updatedDate: LocalDateTime,
)
