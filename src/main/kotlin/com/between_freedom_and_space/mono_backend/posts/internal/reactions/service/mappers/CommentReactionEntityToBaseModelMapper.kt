package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel

class CommentReactionEntityToBaseModelMapper: ModelMapper<CommentReaction, BaseCommentReactionModel> {

    override fun map(original: CommentReaction): BaseCommentReactionModel {
        return BaseCommentReactionModel(
            id = original.id.value,
            reaction = original.reaction,
            commentId = original.comment.value,
            userId = original.reactionBy.value,
            isDeleted = original.isDeleted,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}