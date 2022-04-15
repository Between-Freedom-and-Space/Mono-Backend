package com.between_freedom_and_space.mono_backend.access.entities.role.models

import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserRolesTable
import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserToRolesTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserRole(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<UserRole>(UserRolesTable)

    var roleAlias by UserRolesTable.roleAlias

    val users by UserToRole referrersOn UserToRolesTable.role

    var createdDate by UserRolesTable.createdDate

    var updatedDate by UserRolesTable.updatedDate
}