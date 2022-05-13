package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.CreateTagRequest
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.CreateTagModel

class CreateTagRequestToCreateModelMapper: ModelMapper<CreateTagRequest, CreateTagModel> {

    override fun map(original: CreateTagRequest): CreateTagModel {
        return CreateTagModel(
            tagAlias = original.tagAlias,
            tagDescription = original.tagDescription
        )
    }
}