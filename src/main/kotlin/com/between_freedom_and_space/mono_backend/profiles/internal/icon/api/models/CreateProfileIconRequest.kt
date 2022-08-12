package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Serializable
data class CreateProfileIconRequest(

    @SerialName("icon_base64")
    @get:NotBlank
    val iconBase64: String,

    @SerialName("profile_id")
    @get:Min(1, message = "Profile id can't be less than 1")
    val profileId: Long,
)
