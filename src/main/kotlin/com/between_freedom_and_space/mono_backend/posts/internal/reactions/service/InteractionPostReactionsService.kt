package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreatePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdatePostReactionModel

interface InteractionPostReactionsService {

    fun create(model: CreatePostReactionModel): BasePostReactionModel

    fun update(reactionId: Long, model: UpdatePostReactionModel): BasePostReactionModel

    fun delete(reactionId: Long): BasePostReactionModel
}