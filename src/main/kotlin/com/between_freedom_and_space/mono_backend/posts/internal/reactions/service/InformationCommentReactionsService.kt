package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.ReactionId

interface InformationCommentReactionsService {

    fun getAllReactions(pageNumber: Int, pageSize: Int): List<BaseCommentReactionModel>

    fun getReactionById(reactionId: Long): BaseCommentReactionModel

    fun getReactions(ids: Collection<ReactionId>): List<BaseCommentReactionModel>

    fun getReactionsWithCommentId(commentId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentReactionModel>

    fun getReactionsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentReactionModel>
}