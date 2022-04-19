package com.between_freedom_and_space.mono_backend.posts.repository

import com.between_freedom_and_space.mono_backend.posts.entities.post.Post

interface CommonPostRepository {

    fun getAllPosts(pageNumber: Int, pageSize: Int): List<Post>

    fun getPostById(id: Long): Post
}