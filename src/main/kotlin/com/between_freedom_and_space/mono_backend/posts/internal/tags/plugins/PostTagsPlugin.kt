package com.between_freedom_and_space.mono_backend.posts.internal.tags.plugins

import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.components.postTagsRoutingAccessors
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.components.postTagsExceptionHandling
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.routing.postTagsInformationRouting
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.routing.postTagsInteractionRouting
import io.ktor.server.application.*

internal fun Application.addPostTagsPlugin() {
    postTagsInformationRouting()
    postTagsInteractionRouting()

    postTagsExceptionHandling()
    postTagsRoutingAccessors()
}

