package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.CreatePostReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreatePostReactionModel

class CreatePostRequestToCreateModelMapper: ModelMapper<CreatePostReactionRequest, CreatePostReactionModel> {

    override fun map(original: CreatePostReactionRequest): CreatePostReactionModel {
        return CreatePostReactionModel(
            reaction = original.reaction,
            postId = original.postId,
            authorId = original.authorId,
        )
    }
}