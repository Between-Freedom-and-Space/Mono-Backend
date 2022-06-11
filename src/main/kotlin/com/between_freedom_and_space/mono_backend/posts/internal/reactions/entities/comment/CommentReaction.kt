package com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CommentReaction(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<CommentReaction>(CommentReactionsTable)

    var reaction by CommentReactionsTable.reaction

    var isDeleted by CommentReactionsTable.isDeleted

    var comment by CommentReactionsTable.comment

    var reactionBy by CommentReactionsTable.reactionBy

    var createdDate by CommentReactionsTable.createdDate

    var updatedDate by CommentReactionsTable.updatedDate

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}