package com.between_freedom_and_space.mono_backend.auth.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

@Serializable
data class AuthenticateUserRequest(

    @SerialName("nickname")
    @get:NotBlank(message = "Nickname can't be empty")
    @get:Length(message = "Invalid nickname length. Min: 3, Max: 20", min = 3, max = 20)
    val nickname: String,

    @SerialName("password_encoded")
    @get:NotBlank(message = "Password can't be empty")
    val passwordEncoded: String,
)
