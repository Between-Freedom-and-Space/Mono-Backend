package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonPostReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.ReactionNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.mappers.PostReactionEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.ReactionId
import org.jetbrains.exposed.sql.transactions.transaction

class InformationPostReactionsServiceImpl(
    private val reactionRepository: CommonPostReactionsRepository,
    private val entityMapper: ModelMapper<PostReaction, BasePostReactionModel>
): InformationPostReactionsService {

    override fun getAllReactions(pageNumber: Int, pageSize: Int): List<BasePostReactionModel> {
        val entities = transaction {
            reactionRepository.getAllReactions(pageNumber, pageSize)
        }
        return entities.map { entityMapper.map(it) }
    }

    override fun getReactionById(reactionId: Long): BasePostReactionModel {
        val entity = transaction {
            reactionRepository.getReactionById(reactionId)
                ?: throw ReactionNotFoundException("Post reaction with id: $reactionId not found")
        }
        return entityMapper.map(entity)
    }

    override fun getReactions(ids: Collection<ReactionId>): List<BasePostReactionModel> {
        val entities = transaction {
            reactionRepository.getReactionsByIds(ids.map { it.value })
        }
        return entities.map { entityMapper.map(it) }
    }

    override fun getReactionsWithAuthorId(
        authorId: Long, pageNumber: Int, pageSize: Int
    ): List<BasePostReactionModel> {
        val entities = transaction {
            reactionRepository.getReactionsWithAuthorId(authorId, pageNumber, pageSize)
        }
        return entities.map { entityMapper.map(it) }
    }

    override fun getReactionsWithPostId(
        postId: Long, pageNumber: Int, pageSize: Int
    ): List<BasePostReactionModel> {
        val entities = transaction {
            reactionRepository.getReactionsWithPostId(postId, pageNumber, pageSize)
        }
        return entities.map { entityMapper.map(it) }
    }
}