package com.between_freedom_and_space.mono_backend.app.config.properties

internal data class DatabaseProperties(

    val url: String,

    val driver: String,

    val userName: String,

    val password: String,
)