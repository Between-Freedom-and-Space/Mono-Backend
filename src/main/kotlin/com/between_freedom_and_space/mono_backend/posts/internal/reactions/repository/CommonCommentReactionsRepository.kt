package com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction

interface CommonCommentReactionsRepository {

    fun getAllCommentReactions(pageNumber: Int, pageSize: Int): List<CommentReaction>

    fun getCommentReactionById(reactionId: Long): CommentReaction
}