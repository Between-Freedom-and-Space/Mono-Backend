package com.between_freedom_and_space.mono_backend.posts.repository

import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.services.models.CreatePostModel

interface CommonPostRepository {

    fun getAllPosts(pageNumber: Int, pageSize: Int): List<Post>

    fun getPostById(id: Long): Post?

    fun getPostsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<Post>

    fun createPost(authorId: Long, model: CreatePostModel): Post

    fun savePost(post: Post): Post

    fun deletePost(postId: Long): Post?
}