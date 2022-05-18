package com.between_freedom_and_space.mono_backend.posts.internal.comments.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.repository.CommonCommentsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InformationCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.exceptions.CommentNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.mappers.CommentEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CommentId
import org.jetbrains.exposed.sql.transactions.transaction

class InformationCommentsServiceImpl(
    private val commentRepository: CommonCommentsRepository,
    private val entityMapper: CommentEntityToBaseModelMapper,
): InformationCommentsService {

    override fun getAllComments(pageNumber: Int, pageSize: Int): List<BaseCommentModel> {
        val entities = transaction {
            commentRepository.getAllComments(pageNumber, pageSize)
        }
        return entities.map { entityMapper.map(it) }
    }

    override fun getCommentById(commentId: Long): BaseCommentModel {
        val entity = transaction {
            commentRepository.getCommentById(commentId)
                ?: throw CommentNotFoundException("Comment with id: $commentId not found")
        }
        return entityMapper.map(entity)
    }

    override fun getComments(ids: Collection<CommentId>): List<BaseCommentModel> {
        val entities = transaction {
            commentRepository.getCommentsWithIds(ids.map { it.value })
        }
        return entities.map { entityMapper.map(it) }
    }

    override fun getCommentsWithPostId(
        postId: Long, pageNumber: Int, pageSize: Int
    ): List<BaseCommentModel> {
        val entities = transaction {
            commentRepository.getCommentsWithPostId(postId, pageNumber, pageSize)
        }
        return entities.map { entityMapper.map(it) }
    }

    override fun getCommentsWithAuthorId(
        authorId: Long, pageNumber: Int, pageSize: Int
    ): List<BaseCommentModel> {
        val entities = transaction {
            commentRepository.getCommentsWithAuthorId(authorId, pageNumber, pageSize)
        }
        return entities.map { entityMapper.map(it) }
    }
}