package com.between_freedom_and_space.mono_backend.auth.api.models

data class UserRegisterRequest(

    val nickName: String,

    val mail: String,

    val password: String,

    val nameAlias: String,

    val description: String?,
)
