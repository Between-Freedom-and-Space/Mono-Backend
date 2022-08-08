package com.between_freedom_and_space.mono_backend.popular.repository.impl

import com.between_freedom_and_space.mono_backend.popular.repository.LastCreatedRepository
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.entities.post.PostsTable
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostCommentsTable
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostTagsTable
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class LastCreatedRepositoryImpl: LastCreatedRepository {

    override fun getLastCreatedPosts(pageNumber: Int, pageSize: Int): List<Post> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostsTable
            .selectAll()
            .orderBy(PostsTable.createdDate, SortOrder.DESC_NULLS_LAST)
            .limit(pageSize, offset)
        val result = Post.wrapRows(query)
        return result.toList()
    }

    override fun getLastCreatedProfiles(pageNumber: Int, pageSize: Int): List<UserProfile> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = UserProfilesTable
            .selectAll()
            .orderBy(UserProfilesTable.createdDate, SortOrder.DESC_NULLS_LAST)
            .limit(pageSize, offset)
        val result = UserProfile.wrapRows(query)
        return result.toList()
    }

    override fun getLastCreatedTags(pageNumber: Int, pageSize: Int): List<PostTag> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostTagsTable
            .selectAll()
            .orderBy(PostTagsTable.createdDate, SortOrder.DESC_NULLS_LAST)
            .limit(pageSize, offset)
        val result = PostTag.wrapRows(query)
        return result.toList()
    }

    override fun getLastCreatedComments(pageNumber: Int, pageSize: Int): List<PostComment> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostCommentsTable
            .selectAll()
            .orderBy(PostCommentsTable.createdDate, SortOrder.DESC_NULLS_LAST)
            .limit(pageSize, offset)
        val result = PostComment.wrapRows(query)
        return result.toList()
    }
}