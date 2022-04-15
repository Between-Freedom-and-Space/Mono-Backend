package com.between_freedom_and_space.mono_backend.access.entities.rules.models

import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessRulesTable
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedRolesTable
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedUsersTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AccessRule(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<AccessRule>(AccessRulesTable)

    var pathPattern by AccessRulesTable.pathPattern

    val accessedUsers by AccessedUser referrersOn AccessedUsersTable.accessRule

    val accessedRoles by AccessedRole referrersOn AccessedRolesTable.accessRule

    var lastModifiedBy by AccessRulesTable.lastModifiedBy

    var createdDate by AccessRulesTable.createdDate

    var updatedDate by AccessRulesTable.updatedDate
}