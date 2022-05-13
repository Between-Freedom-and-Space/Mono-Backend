package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

@Serializable
data class CreateTagRequest(

    @SerialName("tag_alias")
    @NotBlank(message = "Tag alias can't be blank")
    @Length(max = 35, message = "Tag alias length can't be more than 35")
    val tagAlias: String,

    @SerialName("tag_description")
    val tagDescription: String?,
)
