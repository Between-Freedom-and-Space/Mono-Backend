package com.between_freedom_and_space.mono_backend.popular.repository

import com.between_freedom_and_space.mono_backend.popular.service.model.PopularPeriod
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile

interface PopularRepository {

    fun getPopularPosts(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<Post>

    fun getPopularTags(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<PostTag>

    fun getPopularComments(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<PostComment>

    fun getPopularProfiles(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<UserProfile>
}