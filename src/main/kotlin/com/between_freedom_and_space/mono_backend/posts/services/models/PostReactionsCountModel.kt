package com.between_freedom_and_space.mono_backend.posts.services.models

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction

data class PostReactionsCountModel(

    val postId: Long,

    val reactionsCount: List<ReactionToCount>
) {

    data class ReactionToCount(val reaction: Reaction, val count: Int)
}
