package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel

class BaseCommentModelToCommentModelMapper: ModelMapper<BaseCommentModel, CommentModel> {

    override fun map(original: BaseCommentModel): CommentModel {
        return CommentModel(
            id = original.id,
            authorId = original.authorId,
            text = original.text,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}