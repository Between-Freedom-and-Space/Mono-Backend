package com.between_freedom_and_space.mono_backend.profiles.services.models

data class UpdateProfileModel(

    val newNickName: String?,

    val newNameAlias: String?,

    val newPasswordEncrypted: String?,

    val newDescription: String?,

    val newMail: String?,

    val newPhoneNumber: String?,

    val newLocation: String?,
)
