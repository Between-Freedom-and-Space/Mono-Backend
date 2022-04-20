package com.between_freedom_and_space.mono_backend.posts.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.PostReactionsCountResponse
import com.between_freedom_and_space.mono_backend.posts.api.models.PostReactionsCountResponse.ReactionToCount
import com.between_freedom_and_space.mono_backend.posts.services.models.PostReactionsCountModel

class PostReactionsCountToReactionsCountResponseMapper: ModelMapper<PostReactionsCountModel, PostReactionsCountResponse> {

    override fun map(original: PostReactionsCountModel): PostReactionsCountResponse {
        return PostReactionsCountResponse(
            postId = original.postId,
            reactionsCount = original.reactionsCount.map {
                ReactionToCount(it.reaction, it.count)
            }
        )
    }
}