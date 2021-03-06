package com.between_freedom_and_space.mono_backend.auth.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenVerifyResultResponse(

    @SerialName("token_type")
    val tokenType: String,

    @SerialName("verify_result")
    val verifyResult: String,
)
