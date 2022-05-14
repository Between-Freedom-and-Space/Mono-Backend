package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreatePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdatePostReactionModel

class InteractionPostReactionsServiceImpl: InteractionPostReactionsService {

    override fun create(model: CreatePostReactionModel): BasePostReactionModel {
        TODO("Not yet implemented")
    }

    override fun update(reactionId: Long, model: UpdatePostReactionModel): BasePostReactionModel {
        TODO("Not yet implemented")
    }

    override fun delete(reactionId: Long): BasePostReactionModel {
        TODO("Not yet implemented")
    }
}