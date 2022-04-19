package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.ReactionId

class InformationPostReactionsServiceImpl: InformationPostReactionsService {

    override fun getAllReactions(pageNumber: Int, pageSize: Int): List<BasePostReactionModel> {
        TODO("Not yet implemented")
    }

    override fun getReactionById(reactionId: Long): BasePostReactionModel {
        TODO("Not yet implemented")
    }

    override fun getReactions(ids: Collection<ReactionId>): List<BasePostReactionModel> {
        TODO("Not yet implemented")
    }

    override fun getReactionsWithAuthorId(
        authorId: Long, pageNumber: Int, pageSize: Int
    ): List<BasePostReactionModel> {
        TODO("Not yet implemented")
    }

    override fun getReactionsWithPostId(
        postId: Long, pageNumber: Int, pageSize: Int
    ): List<BasePostReactionModel> {
        TODO("Not yet implemented")
    }
}