package com.between_freedom_and_space.mono_backend.posts.entities

import org.jetbrains.exposed.sql.Table

object PostsTable: Table("posts") {

    val id = long("id").autoIncrement().entityId()

    val text = text("text")


}