package com.between_freedom_and_space.mono_backend.auth.components.plugin.extensions

import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.auth.components.plugin.util.userAuthorityAttributeKey
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import io.ktor.server.application.*
import io.ktor.util.pipeline.*

fun PipelineContext<Unit, ApplicationCall>.getUserAuthorities(): UserAuthority {
    return getUserAuthoritiesOrNull()
        ?: throw AuthenticateException("User wasn't authenticated")
}

fun PipelineContext<Unit, ApplicationCall>.getUserAuthoritiesOrNull(): UserAuthority? {
    val attributes = call.attributes
    return attributes.getOrNull(userAuthorityAttributeKey)
}