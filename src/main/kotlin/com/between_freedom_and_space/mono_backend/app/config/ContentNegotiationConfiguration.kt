package com.between_freedom_and_space.mono_backend.app.config

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.plugins.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.contentnegotiation.ContentNegotiationConfig
import kotlinx.serialization.json.Json

internal fun ContentNegotiationConfig.configure() {
    json(Json {
        prettyPrint = true
        isLenient = true
    })
}