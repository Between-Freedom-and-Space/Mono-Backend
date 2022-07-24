package com.between_freedom_and_space.mono_backend.access.entities.rules.models

import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessRulesTable
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedRolesTable
import com.between_freedom_and_space.mono_backend.access.entities.rules.tables.AccessedUsersTable
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

@Suppress("DuplicatedCode")
class AccessRule(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<AccessRule>(AccessRulesTable)

    var pathPattern by AccessRulesTable.pathPattern

    val accessedUsers by AccessedUser referrersOn AccessedUsersTable.accessRule

    val accessedRoles by AccessedRole referrersOn AccessedRolesTable.accessRule

    var lastModifiedBy by AccessRulesTable.lastModifiedBy

    var isActive by AccessRulesTable.isActive

    var createdDate by AccessRulesTable.createdDate

    var updatedDate by AccessRulesTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccessRule

        if (id.value != other.id.value) return false
        if (pathPattern != other.pathPattern) return false
        if (lastModifiedBy != other.lastModifiedBy) return false
        if (isActive != other.isActive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pathPattern.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + (lastModifiedBy?.hashCode() ?: 0)
        result = 31 * result + isActive.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}