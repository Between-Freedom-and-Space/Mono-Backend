package com.between_freedom_and_space.mono_backend.common.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Error(

    @SerialName("error_id")
    val errorId: String,

    @SerialName("message")
    val message: String
) {

    companion object {

        const val DEFAULT_MESSAGE = "Something went wrong."

        const val NOT_PRESENTED_ERROR_ID = ""
    }
}