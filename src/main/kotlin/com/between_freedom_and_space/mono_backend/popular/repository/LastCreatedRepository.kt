package com.between_freedom_and_space.mono_backend.popular.repository

import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile

interface LastCreatedRepository {

    fun getLastCreatedPosts(pageNumber: Int, pageSize: Int): List<Post>

    fun getLastCreatedProfiles(pageNumber: Int, pageSize: Int): List<UserProfile>

    fun getLastCreatedTags(pageNumber: Int, pageSize: Int): List<PostTag>

    fun getLastCreatedComments(pageNumber: Int, pageSize: Int): List<PostComment>
}