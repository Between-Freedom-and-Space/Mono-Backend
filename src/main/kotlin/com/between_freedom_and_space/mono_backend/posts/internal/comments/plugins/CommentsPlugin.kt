package com.between_freedom_and_space.mono_backend.posts.internal.comments.plugins

import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.routing.commentsExceptionHandling
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.routing.commentsInformationRouting
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.routing.commentsInteractionRouting
import io.ktor.server.application.*

fun Application.addCommentsPlugin() {

    commentsExceptionHandling()
    commentsInformationRouting()
    commentsInteractionRouting()
}