package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CreateCommentRequest
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel

class CreateCommentRequestToCreateModelMapper: ModelMapper<CreateCommentRequest, CreateCommentModel> {

    override fun map(original: CreateCommentRequest): CreateCommentModel {
        return CreateCommentModel(
            text = original.commentText,
        )
    }
}