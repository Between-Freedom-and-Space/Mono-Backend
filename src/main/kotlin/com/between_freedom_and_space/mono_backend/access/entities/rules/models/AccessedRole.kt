package com.between_freedom_and_space.mono_backend.access.entities.rules.models

import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedRolesTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AccessedRole(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<AccessedRole>(AccessedRolesTable)

    var role by AccessedRolesTable.role

    var accessRule by AccessedRolesTable.accessRule

    var ruleGivenBy by AccessedRolesTable.ruleGivenBy

    var createdDate by AccessedRolesTable.createdDate

    var updatedDate by AccessedRolesTable.createdDate
}