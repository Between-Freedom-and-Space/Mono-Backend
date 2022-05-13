package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.UpdateCommentRequest
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.UpdateCommentModel

class UpdateCommentRequestToUpdateModelMapper: ModelMapper<UpdateCommentRequest, UpdateCommentModel> {

    override fun map(original: UpdateCommentRequest): UpdateCommentModel {
        return UpdateCommentModel(
            newText = original.newCommentText,
        )
    }
}