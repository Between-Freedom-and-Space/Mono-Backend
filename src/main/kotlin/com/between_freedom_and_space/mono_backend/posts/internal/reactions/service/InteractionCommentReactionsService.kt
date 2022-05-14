package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreateCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdateCommentReactionModel

interface InteractionCommentReactionsService {

    fun create(model: CreateCommentReactionModel): BaseCommentReactionModel

    fun update(reactionId: Long, model: UpdateCommentReactionModel): BaseCommentReactionModel

    fun delete(reactionId: Long): BaseCommentReactionModel
}