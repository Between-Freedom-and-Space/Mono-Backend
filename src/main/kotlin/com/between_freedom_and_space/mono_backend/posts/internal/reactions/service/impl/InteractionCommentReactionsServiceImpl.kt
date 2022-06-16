package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.repository.CommonCommentsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.exceptions.CommentNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonCommentReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.model.CreateCommentReactionEntity
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.ReactionNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreateCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdateCommentReactionModel
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionCommentReactionsServiceImpl(
    private val reactionRepository: CommonCommentReactionsRepository,
    private val commentsRepository: CommonCommentsRepository,
    private val profilesRepository: CommonProfilesRepository,
    private val entityMapper: ModelMapper<CommentReaction, BaseCommentReactionModel>
): InteractionCommentReactionsService {

    override fun createReaction(model: CreateCommentReactionModel): BaseCommentReactionModel {
        val entity = transaction {
            val authorId = model.authorId
            val commentId = model.commentId

            val profile = profilesRepository.getProfileById(authorId)
                ?: throw ProfileNotFoundException("Profile with id: $authorId not found")
            val comment = commentsRepository.getCommentById(commentId)
                ?: throw CommentNotFoundException("Comment with id: $commentId not found")

            val createModel = CreateCommentReactionEntity(model.reaction)
            reactionRepository.create(comment.id, profile.id, createModel)
        }
        return entityMapper.map(entity)
    }

    override fun updateReaction(reactionId: Long, model: UpdateCommentReactionModel): BaseCommentReactionModel {
        val entity = transaction {
            val reaction = reactionRepository.getReactionById(reactionId)
                ?: throw ReactionNotFoundException("Comment reaction with id: $reactionId not found")

            model.newReaction?.let { reaction.reaction = it }
            reactionRepository.save(reaction)
        }
        return entityMapper.map(entity)
    }

    override fun deleteReaction(reactionId: Long): BaseCommentReactionModel {
        val entity = transaction {
            reactionRepository.delete(reactionId)
                ?: throw ReactionNotFoundException("Comment reaction with id $reactionId not found")
        }
        return entityMapper.map(entity)
    }
}