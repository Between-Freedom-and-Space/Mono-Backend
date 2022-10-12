package com.between_freedom_and_space.mono_backend.mailing.plugin.config

import com.between_freedom_and_space.mono_backend.mailing.components.model.EmailServerConfig
import io.ktor.server.application.*

internal fun Application.emailServerConfiguration(): EmailServerConfig {
    val config = environment.config

    val host = config.property("email.host")
    val port = config.property("email.port")
    val secure = config.property("email.secure")
    val authUser = config.property("email.authUser")
    val authPassword = config.property("email.authPassword")

    return EmailServerConfig(
        host = host.getString(),
        port = port.getString().toInt(),
        secure = secure.getString().toBoolean(),
        authUser = authUser.getString(),
        authPassword = authPassword.getString(),
    )
}