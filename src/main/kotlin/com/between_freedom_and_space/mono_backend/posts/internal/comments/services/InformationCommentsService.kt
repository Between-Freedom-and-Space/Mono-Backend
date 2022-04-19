package com.between_freedom_and_space.mono_backend.posts.internal.comments.services

import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CommentId

interface InformationCommentsService {

    fun getAllComments(pageNumber: Int, pageSize: Int): List<BaseCommentModel>

    fun getCommentById(commentId: Long): BaseCommentModel

    fun getComments(ids: Collection<CommentId>): List<BaseCommentModel>

    fun getCommentsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentModel>

    fun getCommentsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentModel>
}