package com.between_freedom_and_space.mono_backend.posts.entities.post

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostsTable: LongIdTable("posts") {

    val text = text("text", eagerLoading = true)

    val isVisible = bool("is_visible").default(true)

    val isDeleted = bool("is_deleted").default(false)

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}