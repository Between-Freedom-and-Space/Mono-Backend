package com.between_freedom_and_space.mono_backend.profiles.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.NotBlank

@Serializable
data class ProfileExistsRequest(

    @SerialName("profile_nickname")
    @get:NotBlank(message = "Nickname can't be blank")
    val nickName: String,
)
