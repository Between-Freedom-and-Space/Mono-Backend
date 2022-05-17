package com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.model.CreateCommentReactionEntity
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreateCommentReactionModel
import org.jetbrains.exposed.dao.id.EntityID

interface CommonCommentReactionsRepository {

    fun getAllReactions(pageNumber: Int, pageSize: Int): List<CommentReaction>

    fun getReactionById(reactionId: Long): CommentReaction?

    fun getReactionsByIds(ids: Collection<Long>): List<CommentReaction>

    fun getReactionsWithCommentId(commentId: Long, pageNumber: Int, pageSize: Int): List<CommentReaction>

    fun getReactionsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<CommentReaction>

    fun create(commentId: EntityID<Long>, authorId: EntityID<Long>, model: CreateCommentReactionEntity): CommentReaction

    fun save(reaction: CommentReaction): CommentReaction

    fun delete(reactionId: Long): CommentReaction?
}