package com.between_freedom_and_space.mono_backend.profiles.internal.sessions.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserSessionsTable: LongIdTable("user_sessions") {

    val location = text("location", eagerLoading = true)

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}