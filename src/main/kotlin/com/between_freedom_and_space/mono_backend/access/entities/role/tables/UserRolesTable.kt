package com.between_freedom_and_space.mono_backend.access.entities.role.tables

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserRolesTable: LongIdTable("user_role") {

    val roleAlias = enumeration("role_alias", Role::class)

    val createdDate = datetime("created_date").clientDefault { localDateTimeNow() }

    val updatedDate = datetime("updated_date")
}