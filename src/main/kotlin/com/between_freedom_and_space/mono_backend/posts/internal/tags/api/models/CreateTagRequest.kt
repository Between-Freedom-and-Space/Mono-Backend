package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTagRequest(

    @SerialName("tag_alias")
    val tagAlias: String,

    @SerialName("tag_description")
    val tagDescription: String?,
)
