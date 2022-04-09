package com.between_freedom_and_space.mono_backend.common.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(

    @SerialName("id")
    val id: Long,

    @SerialName("message")
    val message: String
) {

    companion object {

        const val DEFAULT_MESSAGE = "Something went wrong."
    }
}