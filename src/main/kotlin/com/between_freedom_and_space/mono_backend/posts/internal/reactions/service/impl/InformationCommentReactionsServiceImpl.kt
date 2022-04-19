package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.ReactionId

class InformationCommentReactionsServiceImpl: InformationCommentReactionsService {

    override fun getAllReactions(pageNumber: Int, pageSize: Int): List<BaseCommentReactionModel> {
        TODO("Not yet implemented")
    }

    override fun getReactionById(reactionId: Long): BaseCommentReactionModel {
        TODO("Not yet implemented")
    }

    override fun getReactions(ids: Collection<ReactionId>): List<BaseCommentReactionModel> {
        TODO("Not yet implemented")
    }

    override fun getReactionsWithCommentId(
        commentId: Long, pageNumber: Int, pageSize: Int
    ): List<BaseCommentReactionModel> {
        TODO("Not yet implemented")
    }

    override fun getReactionsWithAuthorId(
        authorId: Long, pageNumber: Int, pageSize: Int
    ): List<BaseCommentReactionModel> {
        TODO("Not yet implemented")
    }
}