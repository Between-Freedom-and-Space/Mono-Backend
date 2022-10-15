package com.between_freedom_and_space.mono_backend.mailing.api.models

import com.between_freedom_and_space.mono_backend.mailing.api.validators.TelephoneNumber
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.NotBlank

@Serializable
data class VerifyPhoneVerificationCodeRequest(

    @SerialName("verification_code")
    @get:NotBlank(message = "Verification code can't be blank")
    val verificationCode: String,

    @SerialName("target_phone")
    @get:NotBlank(message = "Target phone can't be blank")
    @get:TelephoneNumber(message = "Invalid telephone number")
    val targetPhone: String,
)
