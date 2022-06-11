package com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostToTagTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PostToTag(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<PostToTag>(PostToTagTable)

    var post by PostToTagTable.post

    var tag by PostToTagTable.tag

    var createdDate by PostToTagTable.createdDate

    var updatedDate by PostToTagTable.updatedDate

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}