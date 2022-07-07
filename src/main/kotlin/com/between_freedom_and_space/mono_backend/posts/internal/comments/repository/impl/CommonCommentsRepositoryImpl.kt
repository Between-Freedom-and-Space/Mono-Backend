package com.between_freedom_and_space.mono_backend.posts.internal.comments.repository.impl

import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostCommentsTable
import com.between_freedom_and_space.mono_backend.posts.internal.comments.repository.CommonCommentsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.comments.repository.exceptions.CommentAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.select

class CommonCommentsRepositoryImpl: CommonCommentsRepository {

    override fun getAllComments(pageNumber: Int, pageSize: Int): List<PostComment> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostCommentsTable
            .select {
                PostCommentsTable.isDeleted eq false
            }
            .limit(pageSize, offset)
        val result = PostComment.wrapRows(query)
        return result.toList()
    }

    override fun getCommentsWithIds(ids: Collection<Long>): List<PostComment> {
        val query = PostCommentsTable
            .select {
                PostCommentsTable.id inList ids
            }
        val result = PostComment.wrapRows(query)
        return result.toList()
    }

    override fun getCommentById(commentId: Long): PostComment? {
        return PostComment.findById(commentId)
    }

    override fun createComment(
        authorId: EntityID<Long>, postId: EntityID<Long>, model: CreateCommentModel
    ): PostComment {
        return PostComment.new {
            post = postId
            text = model.text
            author = authorId
        }
    }

    override fun saveComment(comment: PostComment): PostComment {
        comment.flush()
        return comment
    }

    override fun deleteComment(commentId: Long): PostComment? {
        val comment = PostComment.findById(commentId)
            ?: return null
        if (comment.isDeleted) {
            throw CommentAlreadyDeletedException("Comment with id: $commentId already deleted")
        }
        comment.isDeleted = true
        return comment
    }

    override fun getCommentsWithPostId(
        postId: Long, pageNumber: Int, pageSize: Int
    ): List<PostComment> {
        val offset = (pageNumber - 1).toLong() * pageSize.toLong()
        val query = PostCommentsTable
            .select {
                PostCommentsTable.post eq postId
            }
            .limit(pageSize, offset)
        val result = PostComment.wrapRows(query)
        return result.toList()
    }

    override fun getCommentsWithAuthorId(
        authorId: Long, pageNumber: Int, pageSize: Int
    ): List<PostComment> {
        val offset = (pageNumber - 1).toLong() * pageSize.toLong()
        val query = PostCommentsTable
            .select {
                PostCommentsTable.author eq authorId
            }
            .limit(pageSize, offset)
        val result = PostComment.wrapRows(query)
        return result.toList()
    }
}