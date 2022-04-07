package com.between_freedom_and_space.mono_backend.popular.entity.base

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

open class BasePopularTable(tableName: String): LongIdTable(tableName) {

    val popularInstantFrom = datetime("popular_instant_from")

    val popularInstantTo = datetime("popular_instant_to")

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}