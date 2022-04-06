package com.between_freedom_and_space.mono_backend.auth.api.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalTime
import kotlin.time.Duration

@Serializable
data class UserAuthSettingsModel(

    @SerialName("id")
    val id: Long,

    @SerialName("access_token_lifetime")
    val accessTokenLifetime: Instant,

    @SerialName("refresh_token_lifetime")
    val refreshTokenLifetime: Instant,
)
