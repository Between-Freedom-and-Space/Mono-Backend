package com.between_freedom_and_space.mono_backend.profiles.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.NotBlank

@Serializable
data class SetProfileIconRequest(

    @SerialName("icon_base64")
    @get:NotBlank(message = "Icon base64 can't be blank")
    val iconBase64: String,
)
