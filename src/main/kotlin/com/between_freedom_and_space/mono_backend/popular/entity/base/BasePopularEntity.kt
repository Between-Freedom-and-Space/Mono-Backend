package com.between_freedom_and_space.mono_backend.popular.entity.base

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

abstract class BasePopularEntity(id: EntityID<Long>): LongEntity(id) {

    abstract var popularInstantFrom: LocalDateTime

    abstract var popularInstantTo: LocalDateTime

    abstract var createdDate: LocalDateTime

    abstract var updatedDate: LocalDateTime
}