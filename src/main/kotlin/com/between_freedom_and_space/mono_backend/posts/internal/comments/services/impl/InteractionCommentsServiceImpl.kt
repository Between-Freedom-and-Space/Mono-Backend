package com.between_freedom_and_space.mono_backend.posts.internal.comments.services.impl

import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InteractionCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.UpdateCommentModel

class InteractionCommentsServiceImpl: InteractionCommentsService {

    override fun createComment(authorId: Long, postId: Long, model: CreateCommentModel): BaseCommentModel {
        TODO("Not yet implemented")
    }

    override fun updateComment(commentId: Long, model: UpdateCommentModel): BaseCommentModel {
        TODO("Not yet implemented")
    }

    override fun deleteComment(commentId: Long): BaseCommentModel {
        TODO("Not yet implemented")
    }
}