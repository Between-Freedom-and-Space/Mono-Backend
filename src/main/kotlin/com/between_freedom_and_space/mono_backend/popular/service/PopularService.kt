package com.between_freedom_and_space.mono_backend.popular.service

import com.between_freedom_and_space.mono_backend.popular.service.model.PopularPeriod
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel

interface PopularService {

    fun getPopularPosts(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BasePostModel>

    fun getPopularTags(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseTagModel>

    fun getPopularComments(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseCommentModel>

    fun getPopularProfiles(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseProfileModel>
}