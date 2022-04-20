package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactionModel(

    @SerialName("id")
    val id: Long,

    @SerialName("author_id")
    val authorId: Long,

    @SerialName("reaction")
    val reaction: Reaction,

    @SerialName("created_date")
    val createdDate: LocalDateTime,

    @SerialName("updated_date")
    val updatedDate: LocalDateTime,
)
