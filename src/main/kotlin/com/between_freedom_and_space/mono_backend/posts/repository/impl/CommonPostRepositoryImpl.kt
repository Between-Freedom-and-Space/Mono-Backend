package com.between_freedom_and_space.mono_backend.posts.repository.impl

import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.entities.post.PostsTable
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.repository.exceptions.PostAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.posts.repository.models.CreatePostEntityModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select

class CommonPostRepositoryImpl: CommonPostRepository {

    override fun getAllPosts(pageNumber: Int, pageSize: Int): List<Post> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostsTable
            .select {
                PostsTable.isDeleted eq false
            }
            .limit(pageSize, offset)
        val result = Post.wrapRows(query)
        return result.toList()
    }

    override fun getPostById(id: Long): Post? {
        return Post.findById(id)
    }

    override fun getPostsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<Post> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostsTable
            .select {
                PostsTable.author eq authorId
            }
            .limit(pageSize, offset)
        val result = Post.wrapRows(query)
        return result.toList()
    }

    override fun getPostsWithAuthorIds(
        authorIds: Collection<EntityID<Long>>, pageNumber: Int, pageSize: Int
    ): List<Post> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostsTable
            .select {
                PostsTable.author inList authorIds
            }
            .orderBy(PostsTable.createdDate, SortOrder.DESC_NULLS_LAST)
            .limit(pageSize, offset)
        val result = Post.wrapRows(query)
        return result.toList()
    }

    override fun createPost(authorId: EntityID<Long>, tags: Collection<PostTag>, model: CreatePostEntityModel): Post {
        return Post.new {
            name = model.name
            text = model.text
            isVisible = model.isVisible
            author = authorId
            this.tags = SizedCollection(tags)
        }
    }

    override fun savePost(post: Post): Post {
        post.flush()
        return post
    }

    override fun deletePost(postId: Long): Post? {
        val post = Post.findById(postId)
            ?: return null
        if (post.isDeleted) {
            throw PostAlreadyDeletedException("Post with id: $post already deleted")
        }
        post.isDeleted = true
        return post
    }
}