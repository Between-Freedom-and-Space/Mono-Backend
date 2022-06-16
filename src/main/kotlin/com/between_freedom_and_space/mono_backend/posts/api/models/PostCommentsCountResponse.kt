package com.between_freedom_and_space.mono_backend.posts.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostCommentsCountResponse(

    @SerialName("post_id")
    val postId: Long,

    @SerialName("comments_count")
    val commentsCount: Long,
)
