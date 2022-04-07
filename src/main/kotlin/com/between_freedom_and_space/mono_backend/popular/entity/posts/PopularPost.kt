package com.between_freedom_and_space.mono_backend.popular.entity.posts

import com.between_freedom_and_space.mono_backend.popular.entity.base.BasePopularEntity
import com.between_freedom_and_space.mono_backend.popular.entity.comments.PopularCommentsTable
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PopularPost(id: EntityID<Long>): LongEntity(id), BasePopularEntity {
    companion object: LongEntityClass<PopularPost>(PopularPostsTable)

    override var popularInstantFrom: LocalDateTime by PopularPostsTable.popularInstantFrom

    override var popularInstantTo: LocalDateTime by PopularPostsTable.popularInstantTo

    override var createdDate: LocalDateTime by PopularPostsTable.createdDate

    override var updatedDate: LocalDateTime by PopularPostsTable.updatedDate
}