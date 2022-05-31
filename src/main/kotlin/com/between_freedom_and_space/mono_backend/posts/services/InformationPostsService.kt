package com.between_freedom_and_space.mono_backend.posts.services

import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.PostCommentsCountModel
import com.between_freedom_and_space.mono_backend.posts.services.models.PostReactionsCountModel

interface InformationPostsService {

    fun getAllPosts(pageNumber: Int, pageSize: Int): List<BasePostModel>

    fun getPostById(postId: Long): BasePostModel

    fun getPostComments(postId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentModel>

    fun getPostTags(postId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel>

    fun getPostReactions(postId: Long, pageNumber: Int, pageSize: Int): List<BasePostReactionModel>

    fun getPostReactionsCount(postId: Long): PostReactionsCountModel

    fun getPostCommentsCount(postId: Long): PostCommentsCountModel

    fun getPostsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<BasePostModel>
}