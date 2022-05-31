package com.between_freedom_and_space.mono_backend.profiles.services.models

data class UpdateProfileModel(

    val newNickName: String? = null,

    val newNameAlias: String? = null,

    val newPasswordEncrypted: String? = null,

    val newDescription: String? = null,

    val newMail: String? = null,

    val newPhoneNumber: String? = null,

    val newLocation: String? = null,
)
