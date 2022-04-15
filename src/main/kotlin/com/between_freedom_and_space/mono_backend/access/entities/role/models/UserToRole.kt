package com.between_freedom_and_space.mono_backend.access.entities.role.models

import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserToRolesTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserToRole(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<UserToRole>(UserToRolesTable)

    var role by UserToRolesTable.role

    var user by UserToRolesTable.user

    var createdDate by UserToRolesTable.createdDate

    var updatedDate by UserToRolesTable.updatedDate
}