package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagModel(

    @SerialName("id")
    val id: Long,

    @SerialName("alias")
    val alias: String,

    @SerialName("description")
    val description: String?,

    @SerialName("created_date")
    val createdDate: LocalDateTime,

    @SerialName("updated_date")
    val updatedDate: LocalDateTime,
)
