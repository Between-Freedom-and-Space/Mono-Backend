package com.between_freedom_and_space.mono_backend.posts.internal.comments.entities

import com.between_freedom_and_space.mono_backend.posts.entities.post.PostsTable
import com.between_freedom_and_space.mono_backend.profiles.entities.UserProfilesTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostCommentsTable: LongIdTable("post_comments") {

    val post = reference(
        name = "post_id", foreign = PostsTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val text = text("text", eagerLoading = true)

    val isDeleted = bool("is_deleted").default(false)

    val author = reference(
        name = "author_user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}