package com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.impl

import com.between_freedom_and_space.mono_backend.common.exposed.exists
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostToTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostTagsTable
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostToTagTable
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonTagsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.exceptions.TagAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.CreateTagModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class CommonTagsRepositoryImpl: CommonTagsRepository {

    override fun getAllTags(pageNumber: Int, pageSize: Int): List<PostTag> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostTagsTable
            .selectAll()
            .limit(pageSize, offset)
        val result = PostTag.wrapRows(query)
        return result.toList()
    }

    override fun getAllTagsWithIds(ids: Collection<Long>): List<PostTag> {
        val query = PostTagsTable
            .select {
                PostTagsTable.id inList ids
            }
        val result = PostTag.wrapRows(query)
        return result.toList()
    }

    override fun getTagById(tagId: Long): PostTag? {
        return PostTag.findById(tagId)
    }

    override fun getTagByAlias(tagAlias: String): PostTag? {
        val query = PostTagsTable
            .select {
                PostTagsTable.tagAlias eq tagAlias
            }
        val result = PostTag.wrapRows(query)
        return result.firstOrNull()
    }

    override fun getTagsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<PostTag> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = PostTagsTable
            .select {
                PostTagsTable.author eq authorId
            }
            .limit(pageSize, offset)
        val result = PostTag.wrapRows(query)
        return result.toList()
    }

    override fun getTagsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<PostTag> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val postToTagQuery = PostToTagTable
            .select {
                PostToTagTable.post eq postId
            }
            .limit(pageSize, offset)
        val tagIds = PostToTag.wrapRows(postToTagQuery).map { it.post }
        val tagsQuery = PostTagsTable
            .select {
                PostTagsTable.id inList tagIds
            }
        val result = PostTag.wrapRows(tagsQuery)
        return result.toList()
    }

    override fun createTag(authorId: EntityID<Long>, createModel: CreateTagModel): PostTag {
        return PostTag.new {
            author = authorId
            tagAlias = createModel.tagAlias
            tagDescription = createModel.tagDescription
        }
    }

    override fun createTag(createModel: CreateTagModel): PostTag {
        return PostTag.new {
            tagAlias = createModel.tagAlias
            tagDescription = createModel.tagDescription
        }
    }

    override fun deleteTag(tagAlias: String): PostTag? {
        val query = PostTagsTable
            .select {
                PostTagsTable.tagAlias eq tagAlias
            }
            .firstOrNull() ?: return null
        val result = PostTag.wrapRow(query)
        if (result.isDeleted) {
            throw TagAlreadyDeletedException("Tag with alias: $tagAlias already deleted")
        }
        result.isDeleted = true
        return result
    }

    override fun deleteTag(tagId: Long): PostTag? {
        val tag = PostTag.findById(tagId)
            ?: return null
        if (tag.isDeleted) {
            throw TagAlreadyDeletedException("Tag with id: $tagId already deleted")
        }
        tag.isDeleted = true
        return tag
    }

    override fun saveTag(tag: PostTag): PostTag {
        tag.flush()
        return tag
    }

    override fun tagExists(tagAlias: String): Boolean {
        return PostTagsTable.exists {
            PostTagsTable.tagAlias eq tagAlias
        }
    }
}