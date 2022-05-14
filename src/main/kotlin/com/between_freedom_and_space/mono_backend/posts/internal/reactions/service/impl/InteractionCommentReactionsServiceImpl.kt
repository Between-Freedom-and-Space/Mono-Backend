package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreateCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdateCommentReactionModel

class InteractionCommentReactionsServiceImpl: InteractionCommentReactionsService {

    override fun create(model: CreateCommentReactionModel): BaseCommentReactionModel {
        TODO("Not yet implemented")
    }

    override fun update(reactionId: Long, model: UpdateCommentReactionModel): BaseCommentReactionModel {
        TODO("Not yet implemented")
    }

    override fun delete(reactionId: Long): BaseCommentReactionModel {
        TODO("Not yet implemented")
    }
}