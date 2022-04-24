package com.between_freedom_and_space.mono_backend.profiles.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubscribeActionResponse(

    @SerialName("action_result")
    val result: String,

    @SerialName("action")
    val message: String,
)
