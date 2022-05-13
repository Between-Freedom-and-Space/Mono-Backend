package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.UpdateTagRequest
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.UpdateTagModel

class UpdateTagRequestToUpdateModelMapper: ModelMapper<UpdateTagRequest, UpdateTagModel> {

    override fun map(original: UpdateTagRequest): UpdateTagModel {
        return UpdateTagModel(
            newAlias = original.newTagAlias,
            newDescription = original.newTagDescription,
        )
    }
}