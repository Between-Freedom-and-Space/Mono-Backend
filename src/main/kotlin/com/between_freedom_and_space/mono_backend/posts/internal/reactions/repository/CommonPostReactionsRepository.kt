package com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.model.CreatePostReactionEntity
import org.jetbrains.exposed.dao.id.EntityID

interface CommonPostReactionsRepository {

    fun getAllReactions(pageNumber: Int, pageSize: Int): List<PostReaction>

    fun getReactionById(reactionId: Long): PostReaction?

    fun getReactionsByIds(ids: Collection<Long>): List<PostReaction>

    fun getReactionsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<PostReaction>

    fun getReactionsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<PostReaction>

    fun create(authorId: EntityID<Long>, postId: EntityID<Long>, model: CreatePostReactionEntity): PostReaction

    fun save(reaction: PostReaction): PostReaction

    fun delete(reactionId: Long): PostReaction?
}