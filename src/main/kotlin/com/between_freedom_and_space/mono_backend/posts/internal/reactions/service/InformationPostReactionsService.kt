package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.ReactionId

interface InformationPostReactionsService {

    fun getAllReactions(pageNumber: Int, pageSize: Int): List<BasePostReactionModel>

    fun getReactionById(reactionId: Long): BasePostReactionModel

    fun getReactions(ids: Collection<ReactionId>): List<BasePostReactionModel>

    fun getReactionsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<BasePostReactionModel>

    fun getReactionsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<BasePostReactionModel>
}