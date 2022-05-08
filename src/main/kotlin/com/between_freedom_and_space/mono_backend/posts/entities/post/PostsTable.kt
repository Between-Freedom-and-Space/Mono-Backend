package com.between_freedom_and_space.mono_backend.posts.entities.post

import com.between_freedom_and_space.mono_backend.posts.entities.settings.PostsSettingsTable
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostsTable: LongIdTable("posts") {

    val name = text("name", eagerLoading = true)

    val text = text("text", eagerLoading = true)

    val isVisible = bool("is_visible").default(true)

    val isDeleted = bool("is_deleted").default(false)

    val isEdited = bool("is_edited").default(false)

    val author = reference(
        name = "author_user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val settings = reference(
        name = "post_settings_id", foreign = PostsSettingsTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}