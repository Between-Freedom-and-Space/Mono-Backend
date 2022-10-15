package com.between_freedom_and_space.mono_backend.mailing.api.components

import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.mailingRoutingAccessors() {

    routing {
        val basePath = "/mailing/email"

        grantAccessForEveryone("$basePath/verificationCode/send")
        grantAccessForEveryone("$basePath/verificationCode/check")
    }

    routing {
        val basePath = "/mailing/telephone"

        grantAccessForEveryone("$basePath/verificationCode/send")
        grantAccessForEveryone("$basePath/verificationCode/check")
    }
}