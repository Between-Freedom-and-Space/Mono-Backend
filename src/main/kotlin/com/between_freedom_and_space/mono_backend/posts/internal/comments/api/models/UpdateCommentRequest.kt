package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateCommentRequest(

    @SerialName("new_comment_text")
    val newCommentText: String? = null,
)
