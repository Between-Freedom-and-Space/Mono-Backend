package com.between_freedom_and_space.mono_backend.access.api.components

import com.between_freedom_and_space.mono_backend.access.components.exceptions.AccessException
import com.between_freedom_and_space.mono_backend.access.service.exception.InvalidRoleException
import com.between_freedom_and_space.mono_backend.access.service.exception.InvalidRuleException
import com.between_freedom_and_space.mono_backend.access.service.exception.RoleNotFoundException
import com.between_freedom_and_space.mono_backend.access.service.exception.RuleNotFoundException
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.badRequestHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.forbiddenHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.notFoundHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.accessExceptionHandling() {

    forbiddenHandler<AccessException>()

    badRequestHandler<InvalidRoleException>()
    badRequestHandler<InvalidRuleException>()

    notFoundHandler<RoleNotFoundException>()
    notFoundHandler<RuleNotFoundException>()
}