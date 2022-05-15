package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonCommentReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.ReactionNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreateCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdateCommentReactionModel
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionCommentReactionsServiceImpl(
    private val reactionRepository: CommonCommentReactionsRepository,
    private val entityMapper: ModelMapper<CommentReaction, BaseCommentReactionModel>
): InteractionCommentReactionsService {

    override fun create(model: CreateCommentReactionModel): BaseCommentReactionModel {
        val entity = transaction {
            reactionRepository.create(model)
        }
        return entityMapper.map(entity)
    }

    override fun update(reactionId: Long, model: UpdateCommentReactionModel): BaseCommentReactionModel {
        val entity = transaction {
            val reaction = reactionRepository.getReactionById(reactionId)
                ?: throw ReactionNotFoundException("Comment reaction with id: $reactionId not found")

            model.newReaction?.let { reaction.reaction = it }
            reactionRepository.save(reaction)
        }
        return entityMapper.map(entity)
    }

    override fun delete(reactionId: Long): BaseCommentReactionModel {
        val entity = transaction {
            reactionRepository.delete(reactionId)
                ?: throw ReactionNotFoundException("Comment reaction with id $reactionId not found")
        }
        return entityMapper.map(entity)
    }
}