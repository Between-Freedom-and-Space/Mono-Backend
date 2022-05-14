package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.CreateCommentReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreateCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdateCommentReactionModel

class CreateCommentRequestToCreateModelMapper: ModelMapper<CreateCommentReactionRequest, CreateCommentReactionModel> {

    override fun map(original: CreateCommentReactionRequest): CreateCommentReactionModel {
        return CreateCommentReactionModel(
            reaction = original.reaction,
            authorId = original.authorId,
            commentId = original.commentId,
        )
    }
}