package com.between_freedom_and_space.mono_backend.posts.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.UpdatePostRequest
import com.between_freedom_and_space.mono_backend.posts.services.models.UpdatePostModel

class UpdatePostRequestToUpdateModelMapper: ModelMapper<UpdatePostRequest, UpdatePostModel> {

    override fun map(original: UpdatePostRequest): UpdatePostModel {
        return UpdatePostModel(
            newName = original.newName,
            newText = original.newText,
            newVisibility = original.isVisible,
            newTagsAliases = original.newTags,
        )
    }
}