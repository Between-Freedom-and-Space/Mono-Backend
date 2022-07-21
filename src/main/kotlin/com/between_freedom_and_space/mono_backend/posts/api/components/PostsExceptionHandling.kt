package com.between_freedom_and_space.mono_backend.posts.api.components

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.badRequestHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.notFoundHandler
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.InvalidPostException
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.PostNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.postsExceptionHandling() {

    badRequestHandler<InvalidPostException>()

    notFoundHandler<PostNotFoundException>()
}