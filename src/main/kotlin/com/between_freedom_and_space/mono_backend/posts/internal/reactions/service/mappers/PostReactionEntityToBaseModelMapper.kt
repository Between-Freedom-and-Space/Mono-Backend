package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel

class PostReactionEntityToBaseModelMapper: ModelMapper<PostReaction, BasePostReactionModel> {

    override fun map(original: PostReaction): BasePostReactionModel {
        return BasePostReactionModel(
            id = original.id.value,
            postId = original.post.value,
            userId = original.reactionBy.value,
            reaction = original.reaction,
            isDeleted = original.isDeleted,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}