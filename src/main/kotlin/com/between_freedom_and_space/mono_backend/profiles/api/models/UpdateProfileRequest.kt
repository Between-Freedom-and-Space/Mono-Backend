package com.between_freedom_and_space.mono_backend.profiles.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(

    @SerialName("new_nickname")
    val newNickName: String? = null,

    @SerialName("new_name_alias")
    val newNameAlias: String? = null,

    @SerialName("new_password_encrypted")
    val newPasswordEncrypted: String? = null,

    @SerialName("new_description")
    val newDescription: String? = null,

    @SerialName("new_mail")
    val newMail: String? = null,

    @SerialName("new_phone_number")
    val newPhoneNumber: String? = null,

    @SerialName("new_location")
    val newLocation: String? = null,
)
