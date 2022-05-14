package com.between_freedom_and_space.mono_backend.posts.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InteractionCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreatePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.services.ActionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.PostNotFoundException
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import org.jetbrains.exposed.sql.transactions.transaction

class ActionPostsServiceImpl(
    private val postRepository: CommonPostRepository,
    private val reactionService: InteractionPostReactionsService,
    private val commentService: InteractionCommentsService,
    private val postMapper: ModelMapper<Post, BasePostModel>,
): ActionPostsService {

    override fun reactPost(reaction: Reaction, postId: Long, authorId: Long): BasePostModel {
        val createReactionModel = CreatePostReactionModel(
            reaction, postId, authorId
        )
        val resultPost = transaction {
            reactionService.create(createReactionModel)
            postRepository.getPostById(postId)
                ?: throw PostNotFoundException("Post with id: $postId not found")
        }
        return postMapper.map(resultPost)
    }

    override fun commentPost(text: String, postId: Long, authorId: Long): BasePostModel {
        val createCommentModel = CreateCommentModel(text)
        val resultPost = transaction {
            commentService.createComment(authorId, postId, createCommentModel)
            postRepository.getPostById(postId)
                ?: throw PostNotFoundException("Post with id: $postId not found")
        }
        return postMapper.map(resultPost)
    }
}