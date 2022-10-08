package com.between_freedom_and_space.mono_backend.popular.repository.impl

import com.between_freedom_and_space.mono_backend.popular.repository.PopularRepository
import com.between_freedom_and_space.mono_backend.popular.service.model.PopularPeriod
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.entities.post.PostsTable
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostCommentsTable
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostTagsTable
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNowMinus
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.select
import java.util.concurrent.TimeUnit

class StubPopularRepository: PopularRepository {

    override fun getPopularPosts(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<Post> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostsTable
            .select {
                val limitDate = getLimitDate(period)
                PostsTable.createdDate.greater(limitDate)
            }
            .limit(pageSize, offset)
        val result = Post.wrapRows(query)
        return result.toList()
    }

    override fun getPopularTags(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<PostTag> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostTagsTable
            .select {
                val limitDate = getLimitDate(period)
                PostTagsTable.createdDate.greater(limitDate)
            }
            .limit(pageSize, offset)
        val result = PostTag.wrapRows(query)
        return result.toList()
    }

    override fun getPopularComments(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<PostComment> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostCommentsTable
            .select {
                val limitDate = getLimitDate(period)
                PostCommentsTable.createdDate.greater(limitDate)
            }
            .limit(pageSize, offset)
        val result = PostComment.wrapRows(query)
        return result.toList()
    }

    override fun getPopularProfiles(
        period: PopularPeriod,
        pageNumber: Int,
        pageSize: Int
    ): List<UserProfile> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = UserProfilesTable
            .select {
                val limitDate = getLimitDate(period)
                UserProfilesTable.createdDate.greater(limitDate)
            }
            .limit(pageSize, offset)
        val result = UserProfile.wrapRows(query)
        return result.toList()
    }

    private fun getLimitDate(period: PopularPeriod): LocalDateTime {
        return when(period) {
            PopularPeriod.HOUR -> localDateTimeNowMinus(TimeUnit.HOURS.toMillis(1L))
            PopularPeriod.DAY -> localDateTimeNowMinus(TimeUnit.DAYS.toMillis(1L))
            PopularPeriod.WEEK -> localDateTimeNowMinus(TimeUnit.DAYS.toMillis(7))
            PopularPeriod.MONTH -> localDateTimeNowMinus(TimeUnit.DAYS.toMillis(30L))
        }
    }
}