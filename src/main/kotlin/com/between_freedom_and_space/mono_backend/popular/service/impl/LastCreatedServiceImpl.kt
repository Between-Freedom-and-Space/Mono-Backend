package com.between_freedom_and_space.mono_backend.popular.service.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.popular.repository.LastCreatedRepository
import com.between_freedom_and_space.mono_backend.popular.service.LastCreatedService
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

class LastCreatedServiceImpl(
    private val repository: LastCreatedRepository,
    private val postMapper: ModelMapper<Post, BasePostModel>,
    private val profileMapper: ModelMapper<UserProfile, BaseProfileModel>,
    private val tagMapper: ModelMapper<PostTag, BaseTagModel>,
    private val commentMapper: ModelMapper<PostComment, BaseCommentModel>,
): LastCreatedService {

    override fun getLastCreatedPosts(pageNumber: Int, pageSize: Int): List<BasePostModel> {
        val posts = transaction {
            val posts = repository.getLastCreatedPosts(pageNumber, pageSize)

            posts.forEach { post ->
                post.load(Post::tags)
            }

            posts
        }
        return posts.map { postMapper.map(it) }
    }

    override fun getLastCreatedProfiles(pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        val profiles = transaction {
            repository.getLastCreatedProfiles(pageNumber, pageSize)
        }
        return profiles.map { profileMapper.map(it) }
    }

    override fun getLastCreatedTags(pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        val tags = transaction {
            repository.getLastCreatedTags(pageNumber, pageSize)
        }
        return tags.map { tagMapper.map(it) }
    }

    override fun getLastCreatedComments(pageNumber: Int, pageSize: Int): List<BaseCommentModel> {
        val comments = transaction {
            repository.getLastCreatedComments(pageNumber, pageSize)
        }
        return comments.map { commentMapper.map(it) }
    }
}