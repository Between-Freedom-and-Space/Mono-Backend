package com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables

import com.between_freedom_and_space.mono_backend.posts.entities.post.PostsTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PostToTagTable: LongIdTable("post_to_tag") {

    var post = reference(
        name = "post_id", foreign = PostsTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    var tag = reference(
        name = "tag_id", foreign = PostTagsTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    var isDeleted = bool("is_deleted").default(false)

    var createdDate = datetime("created_date")

    var updatedDate = datetime("updated_date")
}