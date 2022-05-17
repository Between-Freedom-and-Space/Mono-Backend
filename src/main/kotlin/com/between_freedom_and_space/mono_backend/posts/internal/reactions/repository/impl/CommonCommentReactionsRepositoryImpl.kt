package com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.impl

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReactionsTable
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonCommentReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.exceptions.ReactionAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.model.CreateCommentReactionEntity
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreateCommentReactionModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class CommonCommentReactionsRepositoryImpl: CommonCommentReactionsRepository {

    override fun getAllReactions(pageNumber: Int, pageSize: Int): List<CommentReaction> {
        val offset = (pageNumber - 1).toLong() *  pageSize
        val query = CommentReactionsTable
            .selectAll()
            .limit(pageSize, offset)
        val result = CommentReaction.wrapRows(query)
        return result.toList()
    }

    override fun getReactionById(reactionId: Long): CommentReaction? {
        return CommentReaction.findById(reactionId)
    }

    override fun getReactionsByIds(ids: Collection<Long>): List<CommentReaction> {
        val query = CommentReactionsTable
            .select {
                CommentReactionsTable.id inList ids
            }
        val result = CommentReaction.wrapRows(query)
        return result.toList()
    }

    override fun getReactionsWithCommentId(commentId: Long, pageNumber: Int, pageSize: Int): List<CommentReaction> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = CommentReactionsTable
            .select {
                CommentReactionsTable.comment eq commentId
            }
            .limit(pageSize, offset)
        val result = CommentReaction.wrapRows(query)
        return result.toList()
    }

    override fun getReactionsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<CommentReaction> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = CommentReactionsTable
            .select {
                CommentReactionsTable.reactionBy eq authorId
            }
            .limit(pageSize, offset)
        val result = CommentReaction.wrapRows(query)
        return result.toList()
    }

    override fun create(
        commentId: EntityID<Long>, authorId: EntityID<Long>, model: CreateCommentReactionEntity
    ): CommentReaction {
        return CommentReaction.new {
            reaction = model.reaction
            comment = commentId
            reactionBy = authorId
        }
    }

    override fun save(reaction: CommentReaction): CommentReaction {
        reaction.flush()
        return reaction
    }

    override fun delete(reactionId: Long): CommentReaction? {
        val reaction = CommentReaction.findById(reactionId)
            ?: return null
        if (reaction.isDeleted) {
            throw ReactionAlreadyDeletedException("Comment reaction with id: $reaction alredy deletd")
        }
        reaction.isDeleted = true
        return reaction
    }
}