package com.between_freedom_and_space.mono_backend.profiles.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.NotBlank

@Serializable
data class CreateProfileRequest(

    @SerialName("nickname")
    @NotBlank(message = "Nickname can't be blank")
    val nickName: String,

    @SerialName("name_alias")
    @NotBlank(message = "Name alias can't be blank")
    val nameAlias: String,

    @SerialName("password_encrypted")
    @NotBlank(message = "Password can't be blank")
    val passwordEncrypted: String,

    @SerialName("description")
    @NotBlank(message = "Description can't be blank")
    val description: String,

    @SerialName("mail")
    val mail: String?,

    @SerialName("phone_number")
    val phoneNumber: String?,

    @SerialName("location")
    @NotBlank(message = "Location can't be blank")
    val location: String,
)
