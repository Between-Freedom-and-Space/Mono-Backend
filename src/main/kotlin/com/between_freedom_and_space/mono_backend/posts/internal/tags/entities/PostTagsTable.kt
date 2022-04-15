package com.between_freedom_and_space.mono_backend.posts.internal.tags.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostTagsTable: LongIdTable("post_tags") {

    val tagAlias = text("tag_alias", eagerLoading = true)

    val tagDescription = text("tag_description", eagerLoading = true).nullable()

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}