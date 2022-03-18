package com.between_freedom_and_space.mono_backend.profiles.entities

data class UserProfile(

    val id: Long,

    val mail: String,

    val passwordEncrypted: String,

    val nickName: String,

    val nameAlias: String,

    val description: String,

    val location: String,

    val createdDate: String,

    val modifiedDate: String
)
