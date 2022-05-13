package com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables

import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostTagsTable: LongIdTable("post_tags") {

    val author = reference(
        name = "author_user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    ).nullable()

    val tagAlias = text("tag_alias", eagerLoading = true).uniqueIndex()

    val tagDescription = text("tag_description", eagerLoading = true).nullable()

    val isDeleted = bool("is_deleted")

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}