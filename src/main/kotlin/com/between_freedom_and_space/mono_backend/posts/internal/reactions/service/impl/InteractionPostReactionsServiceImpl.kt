package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonPostReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.ReactionNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreatePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdatePostReactionModel
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionPostReactionsServiceImpl(
    private val reactionRepository: CommonPostReactionsRepository,
    private val entityMapper: ModelMapper<PostReaction, BasePostReactionModel>
): InteractionPostReactionsService {

    override fun create(model: CreatePostReactionModel): BasePostReactionModel {
        val entity = transaction {
            reactionRepository.create(model)
        }
        return entityMapper.map(entity)
    }

    override fun update(reactionId: Long, model: UpdatePostReactionModel): BasePostReactionModel {
        val entity = transaction {
            val reaction = reactionRepository.getReactionById(reactionId)
                ?: throw ReactionNotFoundException("Post reaction with id: $reactionId not found")

            model.newReaction?.let { reaction.reaction = it }
            reactionRepository.save(reaction)
        }
        return entityMapper.map(entity)
    }

    override fun delete(reactionId: Long): BasePostReactionModel {
        val entity = transaction {
            reactionRepository.delete(reactionId)
                ?: throw ReactionNotFoundException("Post reaction with id: $reactionId not found")
        }
        return entityMapper.map(entity)
    }
}