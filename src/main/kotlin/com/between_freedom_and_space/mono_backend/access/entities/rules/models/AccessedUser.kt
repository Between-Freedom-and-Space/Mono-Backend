package com.between_freedom_and_space.mono_backend.access.entities.rules.models

import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedUsersTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AccessedUser(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<AccessedUser>(AccessedUsersTable)

    var user by AccessedUsersTable.user

    var accessRule by AccessedUsersTable.accessRule

    var ruleGivenBy by AccessedUsersTable.ruleGivenBy

    var createdDate by AccessedUsersTable.createdDate

    var updatedDate by AccessedUsersTable.updatedDate
}