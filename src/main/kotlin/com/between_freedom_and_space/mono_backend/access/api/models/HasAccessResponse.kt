package com.between_freedom_and_space.mono_backend.access.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HasAccessResponse(

    @SerialName("check_result")
    val result: String,

    @SerialName("path_pattern")
    val pathPattern: String?,
)
