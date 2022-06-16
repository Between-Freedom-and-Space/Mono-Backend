package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Serializable
data class CreateCommentRequest(

    @SerialName("comment_text")
    @get:NotBlank(message = "Comment text can't be blank")
    @get:Length(max = 500, message = "Comment text can't have length more than 500")
    val commentText: String,

    @SerialName("post_id")
    @get:Min(value = 0, message = "Post id can't be less than 0")
    val postId: Long,
)
