package com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostToTagTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PostToTag(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<PostToTag>(PostToTagTable)

    var post by PostToTagTable.post

    var tag by PostToTagTable.tag

    var createdDate by PostToTagTable.createdDate

    var updatedDate by PostToTagTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostToTag

        if (id.value != other.id.value) return false
        if (post != other.post) return false
        if (tag != other.tag) return false

        return true
    }

    override fun hashCode(): Int {
        var result = post.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + tag.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}