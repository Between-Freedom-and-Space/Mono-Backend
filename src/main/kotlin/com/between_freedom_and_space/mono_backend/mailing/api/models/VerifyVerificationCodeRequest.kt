package com.between_freedom_and_space.mono_backend.mailing.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyVerificationCodeRequest(

    @SerialName("verification_code")
    val verificationCode: String,
)
