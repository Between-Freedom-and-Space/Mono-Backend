package com.between_freedom_and_space.mono_backend.auth.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

@Serializable
data class RegisterUserRequest(

    @SerialName("email")
    @NotBlank(message = "Mail can't be empty")
    val mail: String,

    @SerialName("nickname")
    @NotBlank(message = "Nickname can't be empty")
    @Length(message = "Invalid nickname length", min = 3, max = 20)
    val nickName: String,

    @SerialName("password_encrypted")
    @NotBlank(message = "Password can't be empty")
    val passwordEncrypted: String,

    @SerialName("name_alias")
    @NotBlank(message = "Name can't be empty")
    @Length(message = "Invalid name length", min = 2, max = 50)
    val nameAlias: String,

    @SerialName("profile_description")
    @NotBlank(message = "Description can't be empty")
    @Length(message = "Invalid description length", max = 600)
    val description: String,

    @SerialName("location")
    @NotBlank(message = "Location can't be empty")
    @Length(message = "Invalid location length", max = 500)
    val location: String,
)
