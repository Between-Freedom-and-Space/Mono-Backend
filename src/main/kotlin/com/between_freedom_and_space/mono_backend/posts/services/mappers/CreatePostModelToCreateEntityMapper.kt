package com.between_freedom_and_space.mono_backend.posts.services.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.repository.models.CreatePostEntityModel
import com.between_freedom_and_space.mono_backend.posts.services.models.CreatePostModel

class CreatePostModelToCreateEntityMapper: ModelMapper<CreatePostModel, CreatePostEntityModel> {

    override fun map(original: CreatePostModel): CreatePostEntityModel {
        return CreatePostEntityModel(
            name = original.name,
            text = original.text,
            isVisible = original.isVisible,
        )
    }
}