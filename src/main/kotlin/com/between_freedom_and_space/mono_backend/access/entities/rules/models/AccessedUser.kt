package com.between_freedom_and_space.mono_backend.access.entities.rules.models

import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedUsersTable
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AccessedUser(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<AccessedUser>(AccessedUsersTable)

    var user by AccessedUsersTable.user

    var accessRule by AccessedUsersTable.accessRule

    var ruleGivenBy by AccessedUsersTable.ruleGivenBy

    var isActive by AccessedUsersTable.isActive

    var createdDate by AccessedUsersTable.createdDate

    var updatedDate by AccessedUsersTable.updatedDate

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}