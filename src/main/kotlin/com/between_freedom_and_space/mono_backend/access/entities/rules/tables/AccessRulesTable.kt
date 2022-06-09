package com.between_freedom_and_space.mono_backend.access.entities.rules.tables

import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object AccessRulesTable: LongIdTable("access_settings") {

    val pathPattern = text("path_pattern", eagerLoading = true).uniqueIndex()

    val lastModifiedBy = reference(
        name = "last_modified_by_user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    ).nullable()

    val createdDate = datetime("created_date").clientDefault { localDateTimeNow() }

    val updatedDate = datetime("updated_date").clientDefault { localDateTimeNow() }
}