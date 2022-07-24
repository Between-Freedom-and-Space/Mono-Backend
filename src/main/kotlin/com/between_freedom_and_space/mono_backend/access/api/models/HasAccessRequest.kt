package com.between_freedom_and_space.mono_backend.access.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.NotBlank

@Serializable
data class HasAccessRequest(

    @SerialName("raw_path")
    @get:NotBlank(message = "Path can't be blank")
    val rawPath: String
)
