package com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables

import com.between_freedom_and_space.mono_backend.posts.entities.post.PostsTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.id.LongIdTable
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

    var createdDate = datetime("created_date").clientDefault { localDateTimeNow() }

    var updatedDate = datetime("updated_date").clientDefault { localDateTimeNow() }
}