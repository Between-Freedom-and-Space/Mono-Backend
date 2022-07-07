package com.between_freedom_and_space.mono_backend.access.entities.rules.models

import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedUsersTable
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

@Suppress("DuplicatedCode")
class AccessedUser(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<AccessedUser>(AccessedUsersTable)

    var user by AccessedUsersTable.user

    var accessRule by AccessedUsersTable.accessRule

    var ruleGivenBy by AccessedUsersTable.ruleGivenBy

    var isActive by AccessedUsersTable.isActive

    var createdDate by AccessedUsersTable.createdDate

    var updatedDate by AccessedUsersTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccessedUser

        if (id.value != other.id.value) return false
        if (user != other.user) return false
        if (accessRule != other.accessRule) return false
        if (ruleGivenBy != other.ruleGivenBy) return false
        if (isActive != other.isActive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + accessRule.hashCode()
        result = 31 * result + (ruleGivenBy?.hashCode() ?: 0)
        result = 31 * result + isActive.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}