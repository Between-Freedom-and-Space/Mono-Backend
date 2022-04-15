package com.between_freedom_and_space.mono_backend.access.components.plugin.extensions

import com.between_freedom_and_space.mono_backend.access.components.RoutingAccessor
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

fun Routing.registerAccessVerifier(accessor: RoutingAccessor) {

    this.intercept() {

    }
}