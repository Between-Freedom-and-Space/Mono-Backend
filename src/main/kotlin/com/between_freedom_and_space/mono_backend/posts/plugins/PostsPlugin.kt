package com.between_freedom_and_space.mono_backend.posts.plugins

import com.between_freedom_and_space.mono_backend.posts.comments.plugins.addCommentsPlugin
import com.between_freedom_and_space.mono_backend.posts.reactions.plugins.addReactionsPlugin
import com.between_freedom_and_space.mono_backend.posts.routing.postsRouting
import io.ktor.server.application.*

fun Application.addPostsPlugin() {
    // Internal plugins
    addReactionsPlugin()
    addCommentsPlugin()

    // Components
    postsRouting()
}