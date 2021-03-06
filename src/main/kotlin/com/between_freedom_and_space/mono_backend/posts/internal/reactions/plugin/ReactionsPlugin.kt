package com.between_freedom_and_space.mono_backend.posts.internal.reactions.plugin

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.components.reactionsRoutingAccessors
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.routing.comments.commentReactionsInformationRouting
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.routing.comments.commentReactionsInteractionRouting
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.routing.post.postReactionsInformationRouting
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.routing.post.postReactionsInteractionRouting
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.components.reactionsExceptionHandling
import io.ktor.server.application.*

internal fun Application.addReactionsPlugin() {
    commentReactionsInformationRouting()
    commentReactionsInteractionRouting()
    postReactionsInformationRouting()
    postReactionsInteractionRouting()

    reactionsExceptionHandling()
    reactionsRoutingAccessors()
}