package com.between_freedom_and_space.mono_backend.posts.internal.comments.services.impl

import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InformationCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CommentId

class InformationCommentsServiceImpl: InformationCommentsService {

    override fun getAllComments(pageNumber: Int, pageSize: Int): List<BaseCommentModel> {
        TODO("Not yet implemented")
    }

    override fun getCommentById(commentId: Long): BaseCommentModel {
        TODO("Not yet implemented")
    }

    override fun getComments(ids: Collection<CommentId>): List<BaseCommentModel> {
        TODO("Not yet implemented")
    }

    override fun getCommentsWithPostId(
        postId: Long, pageNumber: Int, pageSize: Int
    ): List<BaseCommentModel> {
        TODO("Not yet implemented")
    }

    override fun getCommentsWithAuthorId(
        authorId: Long, pageNumber: Int, pageSize: Int
    ): List<BaseCommentModel> {
        TODO("Not yet implemented")
    }
}