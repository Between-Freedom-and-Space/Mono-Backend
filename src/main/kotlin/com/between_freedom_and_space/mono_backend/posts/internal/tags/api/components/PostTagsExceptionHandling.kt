package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.components

import com.between_freedom_and_space.mono_backend.common.plugins.extensions.badRequestHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.notFoundHandler
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.exceptions.TagAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.InvalidTagException
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.TagNotFoundException
import io.ktor.server.application.*

internal fun Application.postTagsExceptionHandling() {

    notFoundHandler<TagNotFoundException>()

    badRequestHandler<InvalidTagException>()
    badRequestHandler<TagAlreadyDeletedException>()
}