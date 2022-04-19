package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentModel(

    @SerialName("comment_id")
    val id: Long,

    @SerialName("author_id")
    val authorId: Long,

    @SerialName("text")
    val text: String,

    @SerialName("created_date")
    val createdDate: LocalDateTime,

    @SerialName("updated_date")
    val updatedDate: LocalDateTime,
)
