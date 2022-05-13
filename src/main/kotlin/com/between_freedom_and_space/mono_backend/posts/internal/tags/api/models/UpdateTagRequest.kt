package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTagRequest(

    @SerialName("new_tag_alias")
    val newTagAlias: String?,

    @SerialName("new_tag_description")
    val newTagDescription: String?,
)
