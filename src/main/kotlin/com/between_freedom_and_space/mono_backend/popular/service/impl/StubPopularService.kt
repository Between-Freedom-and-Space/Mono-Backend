package com.between_freedom_and_space.mono_backend.popular.service.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.popular.repository.PopularRepository
import com.between_freedom_and_space.mono_backend.popular.service.PopularService
import com.between_freedom_and_space.mono_backend.popular.service.model.PopularPeriod
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.transactions.transaction

class StubPopularService(
    private val repository: PopularRepository,
    private val postMapper: ModelMapper<Post, BasePostModel>,
    private val profileMapper: ModelMapper<UserProfile, BaseProfileModel>,
    private val tagMapper: ModelMapper<PostTag, BaseTagModel>,
    private val commentMapper: ModelMapper<PostComment, BaseCommentModel>,
): PopularService {

    override fun getPopularPosts(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BasePostModel> {
        val posts = transaction {
            val posts = repository.getPopularPosts(period, pageNumber, pageSize)

            posts.forEach { post ->
                post.load(Post::tags)
            }

            posts
        }
        return posts.map { postMapper.map(it) }
    }

    override fun getPopularTags(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseTagModel> {
        val tags = transaction {
            repository.getPopularTags(period, pageNumber, pageSize)
        }
        return tags.map { tagMapper.map(it) }
    }

    override fun getPopularComments(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseCommentModel> {
        val comments = transaction {
            repository.getPopularComments(period, pageNumber, pageSize)
        }
        return comments.map { commentMapper.map(it) }
    }

    override fun getPopularProfiles(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseProfileModel> {
        val profiles = transaction {
            repository.getPopularProfiles(period, pageNumber, pageSize)
        }
        return profiles.map { profileMapper.map(it) }
    }
}