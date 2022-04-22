package com.between_freedom_and_space.mono_backend.posts.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Size

@Serializable
data class UpdatePostRequest(

    @SerialName("new_text")
    val newText: String?,

    @SerialName("is_visible")
    val isVisible: Boolean?,

    @SerialName("new_tags_aliases")
    @Size(max = 25, message = "Invalid new tags length. Max length is 25")
    val newTags: List<String>?,
)
