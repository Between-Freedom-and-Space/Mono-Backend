package com.between_freedom_and_space.mono_backend.posts.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.CreatePostRequest
import com.between_freedom_and_space.mono_backend.posts.services.models.CreatePostModel

class CreatePostRequestToCreateModelMapper: ModelMapper<CreatePostRequest, CreatePostModel> {

    override fun map(original: CreatePostRequest): CreatePostModel {
        return CreatePostModel(
            text = original.postText,
            isVisible = original.isVisible,
            tagsAliases = original.tags
        )
    }
}