package com.between_freedom_and_space.mono_backend.posts.internal.comments.repository

import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment

interface CommonCommentsRepository {

    fun getAllComments(pageNumber: Int, pageSize: Int): List<PostComment>

    fun getCommentById(commentId: Long): PostComment
}