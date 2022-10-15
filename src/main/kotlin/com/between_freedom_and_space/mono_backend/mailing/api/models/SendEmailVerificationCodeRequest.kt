package com.between_freedom_and_space.mono_backend.mailing.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Serializable
data class SendEmailVerificationCodeRequest(

    @SerialName("email")
    @get:Email(message = "Invalid email")
    @get:NotBlank(message = "Email can't be blank")
    val email: String
)
