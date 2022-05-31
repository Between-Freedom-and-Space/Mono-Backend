package com.between_freedom_and_space.mono_backend.auth.plugins.config

import com.between_freedom_and_space.mono_backend.auth.plugins.config.properties.AuthProperties
import io.ktor.server.application.*

internal fun Application.authConfiguration(): AuthProperties {
    val config = environment.config

    val tokenSecret = config.property("auth.tokenSecret")
    val tokenAudience = config.property("auth.tokenAudience")
    val tokenIssuer = config.property("auth.tokenIssuer")

    return AuthProperties(
        tokenSecret = tokenSecret.getString(),
        tokenAudience = tokenAudience.getString(),
        tokenIssuer = tokenIssuer.getString(),
    )
}