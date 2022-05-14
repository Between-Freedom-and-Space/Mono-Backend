package com.between_freedom_and_space.mono_backend.posts.internal.comments.repository

import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel

interface CommonCommentsRepository {

    fun getAllComments(pageNumber: Int, pageSize: Int): List<PostComment>

    fun getCommentsWithIds(ids: Collection<Long>): List<PostComment>

    fun getCommentById(commentId: Long): PostComment?

    fun createComment(authorId: Long, postId: Long, model: CreateCommentModel): PostComment

    fun saveComment(comment: PostComment): PostComment

    fun deleteComment(commentId: Long): PostComment?

    fun getCommentsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<PostComment>

    fun getCommentsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<PostComment>
}