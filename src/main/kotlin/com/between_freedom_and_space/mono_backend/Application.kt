package com.between_freedom_and_space.mono_backend

import com.between_freedom_and_space.mono_backend.plugins.configureRouting
import com.between_freedom_and_space.mono_backend.posts.plugins.addPostsPlugin
import com.between_freedom_and_space.mono_backend.profiles.plugins.addProfilePlugin
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()

        addProfilePlugin()
        addPostsPlugin()
    }.start(wait = true)
}
