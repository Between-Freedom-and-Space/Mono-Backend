package com.between_freedom_and_space.mono_backend.posts.api.models

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostReactionsCountResponse(

    @SerialName("post_id")
    val postId: Long,

    @SerialName("reactions_count")
    val reactionsCount: List<ReactionToCount>
) {

    @Serializable
    data class ReactionToCount(

        @SerialName("reaction_alias")
        val reaction: Reaction,

        @SerialName("count")
        val count: Int
    )
}
