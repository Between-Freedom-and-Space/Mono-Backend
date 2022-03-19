package com.between_freedom_and_space.mono_backend.profiles.internal.settings.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ProfileSettingsTable: LongIdTable("profiles_settings") {

    val isClosedProfile = bool("closed_profile")

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}