package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.UpdateCommentReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdateCommentReactionModel

class UpdateCommentRequestToUpdateModelMapper: ModelMapper<UpdateCommentReactionRequest, UpdateCommentReactionModel> {

    override fun map(original: UpdateCommentReactionRequest): UpdateCommentReactionModel {
        return UpdateCommentReactionModel(
            newReaction = original.newReaction
        )
    }
}