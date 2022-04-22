package com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostReactionsTable: LongIdTable("post_reactions") {

    val reaction = enumeration("reaction_type", Reaction::class)

    val isDeleted = bool("is_deleted").default(false)

    val post = reference(
        name = "post_id", foreign = PostReactionsTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val reactionBy = reference(
        name = "user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}