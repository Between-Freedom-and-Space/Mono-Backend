package com.between_freedom_and_space.mono_backend.popular.service.impl

import com.between_freedom_and_space.mono_backend.popular.service.PopularService
import com.between_freedom_and_space.mono_backend.popular.service.model.PopularPeriod
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel

class StubPopularService: PopularService {

    override fun getPopularPosts(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BasePostModel> {
        TODO("Not yet implemented")
    }

    override fun getPopularTags(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseTagModel> {
        TODO("Not yet implemented")
    }

    override fun getPopularComments(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseCommentModel> {
        TODO("Not yet implemented")
    }

    override fun getPopularProfiles(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseProfileModel> {
        TODO("Not yet implemented")
    }
}