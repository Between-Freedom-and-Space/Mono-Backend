package com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PostReaction(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<PostReaction>(PostReactionsTable)

    var reaction by PostReactionsTable.reaction

    var isDeleted by PostReactionsTable.isDeleted

    var post by PostReactionsTable.post

    var reactionBy by PostReactionsTable.reactionBy

    var createdDate by PostReactionsTable.createdDate

    var updatedDate by PostReactionsTable.updatedDate

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}