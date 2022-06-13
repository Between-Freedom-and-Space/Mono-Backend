package com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostTagsTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PostTag(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<PostTag>(PostTagsTable)

    var author by PostTagsTable.author

    var tagAlias by PostTagsTable.tagAlias

    var tagDescription by PostTagsTable.tagDescription

    var isDeleted by PostTagsTable.isDeleted

    var createdDate by PostTagsTable.createdDate

    var updatedDate by PostTagsTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostTag

        if (id.value != other.id.value) return false
        if (tagAlias != other.tagAlias) return false
        if (tagDescription != other.tagDescription) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tagAlias.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + (tagDescription?.hashCode() ?: 0)
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}