package com.between_freedom_and_space.mono_backend.posts.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.PostCommentsCountResponse
import com.between_freedom_and_space.mono_backend.posts.services.models.PostCommentsCountModel

class PostCommentsCountToCommentsCountResponse: ModelMapper<PostCommentsCountModel, PostCommentsCountResponse> {

    override fun map(original: PostCommentsCountModel): PostCommentsCountResponse {
        return PostCommentsCountResponse(
            postId = original.postId,
            commentsCount = original.commentsCount
        )
    }
}