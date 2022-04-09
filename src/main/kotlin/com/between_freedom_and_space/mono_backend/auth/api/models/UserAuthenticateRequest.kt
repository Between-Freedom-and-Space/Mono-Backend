package com.between_freedom_and_space.mono_backend.auth.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthenticateRequest(

    @SerialName("nickname")
    val nickname: String,

    @SerialName("password_encoded")
    val passwordEncoded: String,
)
