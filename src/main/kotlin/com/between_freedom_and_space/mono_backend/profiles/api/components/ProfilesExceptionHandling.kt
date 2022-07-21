package com.between_freedom_and_space.mono_backend.profiles.api.components

import com.between_freedom_and_space.mono_backend.common.plugins.extensions.badRequestHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.notFoundHandler
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.CreationProfileException
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.InvalidProfileException
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import io.ktor.server.application.*

internal fun Application.profilesExceptionHandling() {

    badRequestHandler<CreationProfileException>()
    badRequestHandler<InvalidProfileException>()

    notFoundHandler<ProfileNotFoundException>()
}