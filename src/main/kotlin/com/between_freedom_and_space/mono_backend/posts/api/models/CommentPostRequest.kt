package com.between_freedom_and_space.mono_backend.posts.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.NotBlank

@Serializable
data class CommentPostRequest(

    @SerialName("comment_text")
    @get:NotBlank(message = "Comment text can't be blank")
    val commentText: String,
)
