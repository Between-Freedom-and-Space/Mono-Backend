package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.ReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel

class BaseCommentReactionModelToReactionModelMapper: ModelMapper<BaseCommentReactionModel, ReactionModel> {

    override fun map(original: BaseCommentReactionModel): ReactionModel {
        return ReactionModel(
            id = original.id,
            authorId = original.userId,
            reaction = original.reaction,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}