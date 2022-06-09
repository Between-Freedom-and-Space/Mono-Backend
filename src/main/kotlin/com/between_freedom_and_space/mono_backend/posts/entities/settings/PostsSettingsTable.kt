package com.between_freedom_and_space.mono_backend.posts.entities.settings

import com.between_freedom_and_space.mono_backend.posts.entities.post.PostsTable
import com.between_freedom_and_space.mono_backend.posts.entities.post.PostsTable.default
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostsSettingsTable: LongIdTable("post_settings") {

    val targetPost = reference(
        name = "target_post_id", foreign = PostsTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val isVisibleForUnauthorizedUsers = bool("is_visible_for_unauthorized_users").default(true)

    val createdDate = datetime("created_date").clientDefault { localDateTimeNow() }

    val updatedDate = datetime("updated_date").clientDefault { localDateTimeNow() }
}