package com.between_freedom_and_space.mono_backend.posts.plugins

import com.between_freedom_and_space.mono_backend.posts.api.routing.postsRouting
import com.between_freedom_and_space.mono_backend.posts.internal.comments.plugins.addCommentsPlugin
import io.ktor.server.application.*

fun Application.addPostsPlugin() {
    addCommentsPlugin()

    // Components
    postsRouting()
}