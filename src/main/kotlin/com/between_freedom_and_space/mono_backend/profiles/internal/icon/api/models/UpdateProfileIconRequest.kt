package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileIconRequest(

    @SerialName("new_icon_base64")
    val newIconBase64: String? = null,
)
