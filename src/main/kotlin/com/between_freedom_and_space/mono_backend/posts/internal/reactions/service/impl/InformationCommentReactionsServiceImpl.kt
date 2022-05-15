package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonCommentReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.ReactionNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.ReactionId
import org.jetbrains.exposed.sql.transactions.transaction

class InformationCommentReactionsServiceImpl(
    private val reactionRepository: CommonCommentReactionsRepository,
    private val entityMapper: ModelMapper<CommentReaction, BaseCommentReactionModel>
): InformationCommentReactionsService {

    override fun getAllReactions(pageNumber: Int, pageSize: Int): List<BaseCommentReactionModel> {
        val entities = transaction {
            reactionRepository.getAllReactions(pageNumber, pageSize)
        }
        return entities.map { entityMapper.map(it) }
    }

    override fun getReactionById(reactionId: Long): BaseCommentReactionModel {
        val entity = transaction {
            reactionRepository.getReactionById(reactionId)
                ?: throw ReactionNotFoundException("Comment reaction with id: $reactionId not found")
        }
        return entityMapper.map(entity)
    }

    override fun getReactions(ids: Collection<ReactionId>): List<BaseCommentReactionModel> {
        val entities = transaction {
            reactionRepository.getReactionsByIds(ids.map { it.value })
        }
        return entities.map { entityMapper.map(it) }
    }

    override fun getReactionsWithCommentId(
        commentId: Long, pageNumber: Int, pageSize: Int
    ): List<BaseCommentReactionModel> {
        val entities = transaction {
            reactionRepository.getReactionsWithCommentId(commentId, pageNumber, pageSize)
        }
        return entities.map { entityMapper.map(it) }
    }

    override fun getReactionsWithAuthorId(
        authorId: Long, pageNumber: Int, pageSize: Int
    ): List<BaseCommentReactionModel> {
        val entities = transaction {
            reactionRepository.getReactionsWithAuthorId(authorId, pageNumber, pageSize)
        }
        return entities.map { entityMapper.map(it) }
    }
}