package com.between_freedom_and_space.mono_backend.posts.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Serializable
data class CreatePostRequest(

    @SerialName("post_text")
    @Length(max = 5000)
    @NotBlank(message = "Post text can't be blank")
    val postText: String,

    @SerialName("is_visible")
    val isVisible: Boolean,

    @SerialName("tags_aliases")
    @Size(max = 25, message = "Invalid tags length. Max length is 25")
    val tags: List<String>,
)
