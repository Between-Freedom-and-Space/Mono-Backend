package com.between_freedom_and_space.mono_backend.posts.comments.entities

import org.jetbrains.exposed.sql.Table

object PostCommentsTable: Table("post_comments") {

    val id = long("id").autoIncrement().entityId()

    val text = text("text")

}