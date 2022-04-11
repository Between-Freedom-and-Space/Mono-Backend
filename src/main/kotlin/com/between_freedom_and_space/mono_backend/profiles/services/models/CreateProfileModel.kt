package com.between_freedom_and_space.mono_backend.profiles.services.models

data class CreateProfileModel(

    val mail: String,

    val passwordEncrypted: String,

    val nickName: String,

    val nameAlias: String,

    val description: String? = null,

    val location: String? = null,
)
