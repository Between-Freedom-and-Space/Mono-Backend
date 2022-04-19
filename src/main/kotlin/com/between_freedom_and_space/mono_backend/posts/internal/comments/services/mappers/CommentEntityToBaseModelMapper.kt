package com.between_freedom_and_space.mono_backend.posts.internal.comments.services.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel

class CommentEntityToBaseModelMapper: ModelMapper<PostComment, BaseCommentModel> {

    override fun map(original: PostComment): BaseCommentModel {
        return BaseCommentModel(
            id = original.id.value,
            postId = original.post.value,
            authorId = original.author.value,
            text = original.text,
            isDeleted = original.isDeleted,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}