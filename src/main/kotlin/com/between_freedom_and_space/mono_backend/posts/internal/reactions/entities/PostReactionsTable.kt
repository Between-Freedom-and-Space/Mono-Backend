package com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostReactionsTable: LongIdTable("post_reactions") {

    val alias = text("alias")

    val reactionBase64 = text("reaction_base64")

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}