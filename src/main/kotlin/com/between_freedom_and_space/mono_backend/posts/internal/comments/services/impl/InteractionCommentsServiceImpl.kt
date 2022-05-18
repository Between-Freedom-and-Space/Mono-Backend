package com.between_freedom_and_space.mono_backend.posts.internal.comments.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.repository.CommonCommentsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InteractionCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.exceptions.CommentNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.UpdateCommentModel
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.PostNotFoundException
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionCommentsServiceImpl(
    private val commentRepository: CommonCommentsRepository,
    private val postsRepository: CommonPostRepository,
    private val profilesRepository: CommonProfilesRepository,
    private val entityMapper: ModelMapper<PostComment, BaseCommentModel>,
): InteractionCommentsService {

    override fun createComment(authorId: Long, postId: Long, model: CreateCommentModel): BaseCommentModel {
        val entity = transaction {
            val author = profilesRepository.getProfileById(authorId)
                ?: throw ProfileNotFoundException("Profile with id: $authorId not found")
            val post = postsRepository.getPostById(postId)
                ?: throw PostNotFoundException("Post with id: $postId not found")
            commentRepository.createComment(author.id, post.id, model)
        }
        return entityMapper.map(entity)
    }

    override fun updateComment(commentId: Long, model: UpdateCommentModel): BaseCommentModel {
        val entity = transaction {
            val comment = commentRepository.getCommentById(commentId)
                ?: throw CommentNotFoundException("Comment with id: $commentId not found")

            model.newText?.let { comment.text = it }

            commentRepository.saveComment(comment)
        }

        return entityMapper.map(entity)
    }

    override fun deleteComment(commentId: Long): BaseCommentModel {
        val entity = transaction {
            commentRepository.deleteComment(commentId)
                ?: throw CommentNotFoundException("Comment with id: $commentId not found")
        }
        return entityMapper.map(entity)
    }
}