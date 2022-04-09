package com.between_freedom_and_space.mono_backend.access.entities

import com.between_freedom_and_space.mono_backend.access.entities.Role
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserRolesTable: LongIdTable("user_role") {

    val role = enumeration("role_alias", Role::class)

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}