package com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment

import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostCommentsTable
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object CommentReactionsTable: LongIdTable("comment_reactions") {

    val reaction = enumeration("reaction_type", Reaction::class)

    val isDeleted = bool("is_deleted").default(false)

    val comment = reference(
        name = "comment_id", foreign = PostCommentsTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val reactionBy = reference(
        name = "user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val createdDate = datetime("created_date").clientDefault { localDateTimeNow() }

    val updatedDate = datetime("updated_date")
}