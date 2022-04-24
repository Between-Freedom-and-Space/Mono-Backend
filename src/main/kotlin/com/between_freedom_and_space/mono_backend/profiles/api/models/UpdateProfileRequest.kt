package com.between_freedom_and_space.mono_backend.profiles.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(

    @SerialName("new_nickname")
    val newNickName: String?,

    @SerialName("new_name_alias")
    val newNameAlias: String?,

    @SerialName("new_password_encrypted")
    val newPasswordEncrypted: String?,

    @SerialName("new_description")
    val newDescription: String?,

    @SerialName("new_mail")
    val newMail: String?,

    @SerialName("new_phone_number")
    val newPhoneNumber: String?,

    @SerialName("new_location")
    val newLocation: String?,
)
