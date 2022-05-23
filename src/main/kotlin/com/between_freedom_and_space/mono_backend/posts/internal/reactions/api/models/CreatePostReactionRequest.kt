package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Min

@Serializable
data class CreatePostReactionRequest(

    @SerialName("post_reaction")
    val reaction: Reaction,

    @SerialName("post_id")
    @get:Min(value = 0, message = "Post id can't be less than 0")
    val postId: Long,

    @SerialName("author_id")
    @get:Min(value = 0, message = "Author id can't be less than 0")
    val authorId: Long,
)
