package com.between_freedom_and_space.mono_backend.auth.plugins.config.properties

data class AuthProperties(

    val tokenSecret: String,

    val tokenAudience: String,

    val tokenIssuer: String,
)