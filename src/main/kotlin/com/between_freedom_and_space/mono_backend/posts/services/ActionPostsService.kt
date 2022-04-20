package com.between_freedom_and_space.mono_backend.posts.services

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel

interface ActionPostsService {

    fun reactPost(reaction: Reaction, postId: Long, authorId: Long): BasePostModel

    fun commentPost(text: String, postId: Long, authorId: Long): BasePostModel
}