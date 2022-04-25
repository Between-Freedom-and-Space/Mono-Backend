package com.between_freedom_and_space.mono_backend.auth.service.model

data class UserAuthModel(

    val id: Long,

    val nickName: String,

    val passwordEncrypted: String,
)
