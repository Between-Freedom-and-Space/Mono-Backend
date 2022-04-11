package com.between_freedom_and_space.mono_backend.profiles.models

data class UserProfileModel(

    val id: Long,

    val mail: String,

    val passwordEncrypted: String,

    val nickName: String,

    val nameAlias: String,

    val description: String?,

    val location: String?,

    val isVisible: Boolean,

    val isDeleted: Boolean,
)
