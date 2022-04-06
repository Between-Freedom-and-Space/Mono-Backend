package com.between_freedom_and_space.mono_backend.auth.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(

    @SerialName("nickname")
    val nickName: String,

    @SerialName("name")
    val mail: String,

    @SerialName("password")
    val passwordHash: String,

    @SerialName("name_alias")
    val nameAlias: String,

    @SerialName("description")
    val description: String?,
)
