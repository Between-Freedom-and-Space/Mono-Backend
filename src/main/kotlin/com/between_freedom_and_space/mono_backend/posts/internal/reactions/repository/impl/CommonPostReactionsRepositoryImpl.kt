package com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.impl

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReactionsTable
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonPostReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.exceptions.ReactionAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.model.CreatePostReactionEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.select

class CommonPostReactionsRepositoryImpl: CommonPostReactionsRepository {

    override fun getAllReactions(pageNumber: Int, pageSize: Int): List<PostReaction> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostReactionsTable
            .select {
                PostReactionsTable.isDeleted eq false
            }
            .limit(pageSize, offset)
        val result = PostReaction.wrapRows(query)
        return result.toList()
    }

    override fun getReactionById(reactionId: Long): PostReaction? {
        return PostReaction.findById(reactionId)
    }

    override fun getReactionsByIds(ids: Collection<Long>): List<PostReaction> {
        val query = PostReactionsTable
            .select {
                PostReactionsTable.id inList ids
            }
        val result = PostReaction.wrapRows(query)
        return result.toList()
    }

    override fun getReactionsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<PostReaction> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostReactionsTable
            .select {
                PostReactionsTable.reactionBy eq authorId
            }
            .limit(pageSize, offset)
        val result = PostReaction.wrapRows(query)
        return result.toList()
    }

    override fun getReactionsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<PostReaction> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostReactionsTable
            .select {
                PostReactionsTable.post eq postId
            }
            .limit(pageSize, offset)
        val result = PostReaction.wrapRows(query)
        return result.toList()
    }

    override fun create(
        authorId: EntityID<Long>, postId: EntityID<Long>, model: CreatePostReactionEntity
    ): PostReaction {
        return PostReaction.new {
            reaction = model.reaction
            post = postId
            reactionBy = authorId
        }
    }

    override fun save(reaction: PostReaction): PostReaction {
        reaction.flush()
        return reaction
    }

    override fun delete(reactionId: Long): PostReaction? {
        val reaction = PostReaction.findById(reactionId)
            ?: return null
        if (reaction.isDeleted) {
            throw ReactionAlreadyDeletedException("Post reaction with id: $reactionId already deleted")
        }
        reaction.isDeleted = true
        return reaction
    }
}