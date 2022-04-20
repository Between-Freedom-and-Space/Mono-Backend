package com.between_freedom_and_space.mono_backend.posts.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.services.ActionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import org.jetbrains.exposed.sql.transactions.transaction

class ActionPostsServiceImpl(
    private val postRepository: CommonPostRepository,
    private val postMapper: ModelMapper<Post, BasePostModel>,
): ActionPostsService {

    override fun reactPost(reaction: Reaction, postId: Long, authorId: Long): BasePostModel {
        val resultPost = transaction {
            val postEntity = postRepository.getPostById(postId)
            postEntity
        }
        return postMapper.map(resultPost)
    }

    override fun commentPost(text: String, postId: Long, authorId: Long): BasePostModel {
        TODO("Not yet implemented")
    }
}