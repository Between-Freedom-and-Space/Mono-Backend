package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonPostReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.model.CreatePostReactionEntity
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.ReactionNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreatePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdatePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.PostNotFoundException
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionPostReactionsServiceImpl(
    private val reactionRepository: CommonPostReactionsRepository,
    private val profilesRepository: CommonProfilesRepository,
    private val postRepository: CommonPostRepository,
    private val entityMapper: ModelMapper<PostReaction, BasePostReactionModel>
): InteractionPostReactionsService {

    override fun createReaction(model: CreatePostReactionModel): BasePostReactionModel {
        val entity = transaction {
            val authorId = model.authorId
            val postId = model.postId

            val profile = profilesRepository.getProfileById(authorId)
                ?: throw ProfileNotFoundException("Profile with id: $authorId not found")
            val post = postRepository.getPostById(postId)
                ?: throw PostNotFoundException("Post with id: $postId not found")
            
            val createModel = CreatePostReactionEntity(model.reaction)
            reactionRepository.create(profile.id, post.id, createModel)
        }
        return entityMapper.map(entity)
    }

    override fun updateReaction(reactionId: Long, model: UpdatePostReactionModel): BasePostReactionModel {
        val entity = transaction {
            val reaction = reactionRepository.getReactionById(reactionId)
                ?: throw ReactionNotFoundException("Post reaction with id: $reactionId not found")

            model.newReaction?.let { reaction.reaction = it }
            reactionRepository.save(reaction)
        }
        return entityMapper.map(entity)
    }

    override fun deleteReaction(reactionId: Long): BasePostReactionModel {
        val entity = transaction {
            reactionRepository.delete(reactionId)
                ?: throw ReactionNotFoundException("Post reaction with id: $reactionId not found")
        }
        return entityMapper.map(entity)
    }
}