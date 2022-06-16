package com.between_freedom_and_space.mono_backend.app.config

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.*
import kotlinx.serialization.json.Json

internal fun ContentNegotiation.Configuration.configure() {
    json(Json {
        prettyPrint = true
        isLenient = true
    })
}