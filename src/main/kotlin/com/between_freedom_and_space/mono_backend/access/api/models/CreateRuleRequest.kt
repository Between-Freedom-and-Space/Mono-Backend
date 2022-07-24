package com.between_freedom_and_space.mono_backend.access.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.NotBlank

@Serializable
data class CreateRuleRequest(

    @SerialName("path_pattern")
    @get:NotBlank(message = "Path pattern can't be blank")
    val pathPattern: String,

    @SerialName("is_active")
    val isActive: Boolean,
)
