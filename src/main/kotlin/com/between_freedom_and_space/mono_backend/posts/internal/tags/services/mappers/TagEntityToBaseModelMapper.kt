package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel

class TagEntityToBaseModelMapper: ModelMapper<PostTag, BaseTagModel> {

    override fun map(original: PostTag): BaseTagModel {
        return BaseTagModel(
            id = original.id.value,
            authorId = original.author?.value,
            alias = original.tagAlias,
            description = original.tagDescription,
            isDeleted = original.isDeleted,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}