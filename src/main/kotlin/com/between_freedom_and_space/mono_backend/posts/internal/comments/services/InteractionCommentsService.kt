package com.between_freedom_and_space.mono_backend.posts.internal.comments.services

import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.UpdateCommentModel

interface InteractionCommentsService {

    fun createComment(authorId: Long, postId: Long, model: CreateCommentModel): BaseCommentModel

    fun updateComment(commentId: Long, model: UpdateCommentModel): BaseCommentModel

    fun deleteComment(commentId: Long): BaseCommentModel
}