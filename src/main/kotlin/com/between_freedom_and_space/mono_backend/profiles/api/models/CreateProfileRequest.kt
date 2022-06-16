package com.between_freedom_and_space.mono_backend.profiles.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.NotBlank

@Serializable
data class CreateProfileRequest(

    @SerialName("nickname")
    @get:NotBlank(message = "Nickname can't be blank")
    val nickName: String,

    @SerialName("name_alias")
    @get:NotBlank(message = "Name alias can't be blank")
    val nameAlias: String,

    @SerialName("password_encrypted")
    @get:NotBlank(message = "Password can't be blank")
    val passwordEncrypted: String,

    @SerialName("description")
    @get:NotBlank(message = "Description can't be blank")
    val description: String,

    @SerialName("mail")
    val mail: String? = null,

    @SerialName("phone_number")
    val phoneNumber: String? = null,

    @SerialName("location")
    @get:NotBlank(message = "Location can't be blank")
    val location: String,
)
