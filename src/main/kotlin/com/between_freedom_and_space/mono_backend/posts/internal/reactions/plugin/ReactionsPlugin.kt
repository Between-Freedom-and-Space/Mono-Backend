package com.between_freedom_and_space.mono_backend.posts.internal.reactions.plugin

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.routing.reactionsExceptionHandling
import io.ktor.server.application.*

internal fun Application.addReactionsPlugin() {

    reactionsExceptionHandling()
}