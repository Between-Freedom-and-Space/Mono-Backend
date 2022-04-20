package com.between_freedom_and_space.mono_backend.posts.services.impl

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import com.between_freedom_and_space.mono_backend.posts.services.ActionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel

class ActionPostsServiceImpl: ActionPostsService {

    override fun reactPost(reaction: Reaction, postId: Long, authorId: Long): BasePostModel {
        TODO("Not yet implemented")
    }

    override fun commentPost(text: String, postId: Long, authorId: Long): BasePostModel {
        TODO("Not yet implemented")
    }
}