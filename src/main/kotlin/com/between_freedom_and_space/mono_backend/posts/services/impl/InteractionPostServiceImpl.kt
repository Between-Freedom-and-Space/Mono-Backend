package com.between_freedom_and_space.mono_backend.posts.services.impl

import com.between_freedom_and_space.mono_backend.posts.services.InteractionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.CreatePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.UpdatePostModel

class InteractionPostServiceImpl: InteractionPostsService {

    override fun createPost(authorId: Long, createPostModel: CreatePostModel): BasePostModel {
        TODO("Not yet implemented")
    }

    override fun updatePost(postId: Long, updateModel: UpdatePostModel): BasePostModel {
        TODO("Not yet implemented")
    }

    override fun deletePost(postId: Long): BasePostModel {
        TODO("Not yet implemented")
    }
}