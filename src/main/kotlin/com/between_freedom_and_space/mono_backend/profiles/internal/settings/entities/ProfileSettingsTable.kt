package com.between_freedom_and_space.mono_backend.profiles.internal.settings.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ProfileSettingsTable: LongIdTable("profiles_settings") {

    val isClosed = bool("is_closed_profile").default(false)

    val isVisible = bool("is_visible_profile").default(true)

    val isDeleted = bool("is_deleted_profile").default(false)

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}