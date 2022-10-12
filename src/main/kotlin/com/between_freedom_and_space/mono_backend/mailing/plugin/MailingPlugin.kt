package com.between_freedom_and_space.mono_backend.mailing.plugin

import com.between_freedom_and_space.mono_backend.mailing.api.components.mailingExceptionHandling
import com.between_freedom_and_space.mono_backend.mailing.api.components.mailingRoutingAccessors
import com.between_freedom_and_space.mono_backend.mailing.api.routing.emailMailingRouting
import com.between_freedom_and_space.mono_backend.mailing.api.routing.telephoneMailingRouting
import io.ktor.server.application.*

internal fun Application.addMailingPlugin() {
    emailMailingRouting()
    telephoneMailingRouting()

    mailingExceptionHandling()
    mailingRoutingAccessors()
}