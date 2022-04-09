package com.between_freedom_and_space.mono_backend.posts.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostsTable: LongIdTable("posts") {

    val text = text("text")

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}