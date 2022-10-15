package com.between_freedom_and_space.mono_backend.mailing.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Serializable
data class VerifyEmailVerificationCodeRequest(

    @SerialName("verification_code")
    @get:NotBlank(message = "Verification code can't be blank")
    val verificationCode: String,

    @SerialName("target_email")
    @get:NotBlank(message = "Target email can't be blank")
    @get:Email(message = "Invalid email")
    val targetEmail: String,
)
