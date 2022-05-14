package com.between_freedom_and_space.mono_backend.posts.internal.tags.repository

import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostToTag

interface CommonPostToTagRepository {

    fun addTagToPost(post: Post, tag: PostTag): PostToTag

    fun addTagsToPost(post: Post, tags: Collection<PostTag>): List<PostToTag>

    fun deleteTagsFromPost(post: Post): List<PostToTag>
}