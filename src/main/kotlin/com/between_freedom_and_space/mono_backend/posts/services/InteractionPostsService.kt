package com.between_freedom_and_space.mono_backend.posts.services

import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.CreatePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.UpdatePostModel

interface InteractionPostsService {

    fun createPost(createPostModel: CreatePostModel): BasePostModel

    fun updatePost(postId: Long, updateModel: UpdatePostModel): BasePostModel

    fun deletePost(postId: Long): BasePostModel
}