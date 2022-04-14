package com.between_freedom_and_space.mono_backend.popular.entity.comments

import com.between_freedom_and_space.mono_backend.popular.entity.base.BasePopularEntity
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PopularComment(id: EntityID<Long>): BasePopularEntity(id) {
    companion object: LongEntityClass<PopularComment>(PopularCommentsTable)

    override var popularInstantFrom: LocalDateTime by PopularCommentsTable.popularInstantFrom

    override var popularInstantTo: LocalDateTime by PopularCommentsTable.popularInstantTo

    override var createdDate: LocalDateTime by PopularCommentsTable.createdDate

    override var updatedDate: LocalDateTime by PopularCommentsTable.updatedDate
}