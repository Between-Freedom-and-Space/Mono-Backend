package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.UpdatePostReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdatePostReactionModel

class UpdatePostRequestToUpdateModelMapper: ModelMapper<UpdatePostReactionRequest, UpdatePostReactionModel> {

    override fun map(original: UpdatePostReactionRequest): UpdatePostReactionModel {
        return UpdatePostReactionModel(
            newReaction = original.newReaction,
        )
    }
}