package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Min

@Serializable
data class CreateCommentReactionRequest(

    @SerialName("comment_reaction")
    val reaction: Reaction,

    @SerialName("author_id")
    @get:Min(value = 0, message = "Author id can't be less than 0")
    val authorId: Long,

    @SerialName("comment_id")
    @get:Min(value = 0, message = "Comment id can't be less than 0")
    val commentId: Long,
)
