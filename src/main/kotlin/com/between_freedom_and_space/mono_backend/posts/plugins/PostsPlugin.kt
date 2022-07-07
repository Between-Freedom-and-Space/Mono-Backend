package com.between_freedom_and_space.mono_backend.posts.plugins

import com.between_freedom_and_space.mono_backend.posts.api.components.postsRoutingAccessors
import com.between_freedom_and_space.mono_backend.posts.api.routing.postsActionRouting
import com.between_freedom_and_space.mono_backend.posts.api.routing.postsExceptionHandling
import com.between_freedom_and_space.mono_backend.posts.api.routing.postsInformationRouting
import com.between_freedom_and_space.mono_backend.posts.api.routing.postsInteractionRouting
import com.between_freedom_and_space.mono_backend.posts.internal.comments.plugins.addCommentsPlugin
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.plugin.addReactionsPlugin
import com.between_freedom_and_space.mono_backend.posts.internal.tags.plugins.addPostTagsPlugin
import io.ktor.server.application.*

fun Application.addPostsPlugin() {
    addCommentsPlugin()
    addReactionsPlugin()
    addPostTagsPlugin()

    postsActionRouting()
    postsInteractionRouting()
    postsInformationRouting()

    postsExceptionHandling()
    postsRoutingAccessors()
}