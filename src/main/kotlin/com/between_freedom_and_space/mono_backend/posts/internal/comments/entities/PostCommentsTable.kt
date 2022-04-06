package com.between_freedom_and_space.mono_backend.posts.internal.comments.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostCommentsTable: LongIdTable("post_comments") {

    val text = text("text")

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}