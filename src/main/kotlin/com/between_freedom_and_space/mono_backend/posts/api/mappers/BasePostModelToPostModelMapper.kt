package com.between_freedom_and_space.mono_backend.posts.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.PostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel

class BasePostModelToPostModelMapper: ModelMapper<BasePostModel, PostModel> {

    override fun map(original: BasePostModel): PostModel {
        return PostModel(
            id = original.id,
            authorId = original.authorId,
            text = original.text,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}