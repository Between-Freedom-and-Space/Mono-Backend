package com.between_freedom_and_space.mono_backend.posts.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequest(

    @SerialName("post_text")
    val postText: String,

    @SerialName("is_visible")
    val isVisible: Boolean,

    @SerialName("tags_aliases")
    val tags: List<String>,
)
