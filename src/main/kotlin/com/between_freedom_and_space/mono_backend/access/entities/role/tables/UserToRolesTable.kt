package com.between_freedom_and_space.mono_backend.access.entities.role.tables

import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserToRolesTable: LongIdTable("user_to_user_roles") {

    val role = reference(
        name = "role_id", foreign = UserRolesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val user = reference(
        name = "user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val createdDate = datetime("created_date").clientDefault { localDateTimeNow() }

    val updatedDate = datetime("updated_date")
}