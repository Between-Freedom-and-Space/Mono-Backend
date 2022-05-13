package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.TagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel

class BaseTagModelToTagModelMapper: ModelMapper<BaseTagModel, TagModel> {

    override fun map(original: BaseTagModel): TagModel {
        return TagModel(
            id = original.id,
            alias = original.alias,
            description = original.description,
            authorId = original.authorId,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}