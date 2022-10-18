package com.between_freedom_and_space.mono_backend.mailing.api.components

import com.between_freedom_and_space.mono_backend.common.plugins.extensions.internalServerErrorHandler
import com.between_freedom_and_space.mono_backend.mailing.components.exceptions.InvalidEmailHtmlTemplateException
import io.ktor.server.application.*

internal fun Application.mailingExceptionHandling() {

    internalServerErrorHandler<InvalidEmailHtmlTemplateException>()
}