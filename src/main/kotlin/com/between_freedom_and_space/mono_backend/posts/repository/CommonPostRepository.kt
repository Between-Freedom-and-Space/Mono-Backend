package com.between_freedom_and_space.mono_backend.posts.repository

import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.repository.models.CreatePostEntityModel
import org.jetbrains.exposed.dao.id.EntityID

interface CommonPostRepository {

    fun getAllPosts(pageNumber: Int, pageSize: Int): List<Post>

    fun getPostById(id: Long): Post?

    fun getPostsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<Post>

    fun getPostsWithAuthorIds(authorIds: Collection<EntityID<Long>>, pageNumber: Int, pageSize: Int): List<Post>

    fun createPost(authorId: EntityID<Long>, tags: Collection<PostTag>, model: CreatePostEntityModel): Post

    fun savePost(post: Post): Post

    fun deletePost(postId: Long): Post?
}