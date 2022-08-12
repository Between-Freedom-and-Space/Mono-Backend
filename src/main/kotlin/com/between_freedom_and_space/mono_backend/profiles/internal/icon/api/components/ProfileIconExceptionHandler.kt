package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.components

import com.between_freedom_and_space.mono_backend.common.plugins.extensions.badRequestHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.notFoundHandler
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.exceptions.InvalidProfileIconException
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.exceptions.ProfileIconNotFoundException
import io.ktor.server.application.*

internal fun Application.profileIconExceptionHandler() {

    badRequestHandler<InvalidProfileIconException>()
    notFoundHandler<ProfileIconNotFoundException>()
}