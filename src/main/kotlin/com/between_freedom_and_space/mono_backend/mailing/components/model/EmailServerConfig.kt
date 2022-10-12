package com.between_freedom_and_space.mono_backend.mailing.components.model

data class EmailServerConfig(
    val host: String,
    val port: Int,
    val secure: Boolean,
    val authUser: String,
    val authPassword: String,
)
