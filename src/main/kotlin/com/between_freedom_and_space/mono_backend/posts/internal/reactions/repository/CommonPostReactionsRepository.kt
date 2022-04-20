package com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction

interface CommonPostReactionsRepository {

    fun getAllPostReactions(pageNumber: Int, pageSize: Int): List<PostReaction>

    fun getPostReactionById(reactionId: Long): PostReaction
}