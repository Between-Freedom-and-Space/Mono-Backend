package com.between_freedom_and_space.mono_backend.access.entities

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserRole(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<UserRole>(UserRolesTable)

    var role by UserRolesTable.role

    var createdDate by UserRolesTable.createdDate

    var updatedDate by UserRolesTable.updatedDate
}