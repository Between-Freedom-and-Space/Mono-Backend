package com.between_freedom_and_space.mono_backend.mailing.api.models

import com.between_freedom_and_space.mono_backend.mailing.api.validators.TelephoneNumber
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Serializable
data class SendPhoneVerificationCodeRequest(

    @SerialName("telephone_number")
    @get:TelephoneNumber(message = "Invalid telephone number")
    @get:NotBlank(message = "Telephone number can't be blank")
    val telephoneNumber: String,

    @SerialName("security_variable")
    @get:NotBlank(message = "Security variable can't be blank")
    val securityVariable: String,
)
