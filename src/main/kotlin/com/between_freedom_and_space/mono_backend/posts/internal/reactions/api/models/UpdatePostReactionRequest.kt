package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePostReactionRequest(

    @SerialName("new_post_reaction")
    val newReaction: Reaction? = null,
)
