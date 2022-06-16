package com.between_freedom_and_space.mono_backend.auth.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Serializable
data class RegisterUserRequest(

    @SerialName("email")
    val mail: String? = null,

    @SerialName("phone_number")
    val phoneNumber: String? = null,

    @SerialName("nickname")
    @get:NotBlank(message = "Nickname can't be empty")
    @get:Length(message = "Invalid nickname length. Min: 3, Max: 20", min = 3, max = 20)
    val nickName: String,

    @SerialName("password_encrypted")
    @get:NotBlank(message = "Password can't be empty")
    val passwordEncrypted: String,

    @SerialName("name_alias")
    @get:NotBlank(message = "Name can't be empty")
    @get:Length(message = "Invalid name length. Min: 2, Max: 50", min = 2, max = 50)
    val nameAlias: String,

    @SerialName("profile_description")
    @get:NotBlank(message = "Description can't be empty")
    @get:Length(message = "Invalid description length. Max: 600", max = 600)
    val description: String,

    @SerialName("location")
    @get:NotBlank(message = "Location can't be empty")
    @get:Length(message = "Invalid location length. Max: 500", max = 500)
    val location: String,
)
