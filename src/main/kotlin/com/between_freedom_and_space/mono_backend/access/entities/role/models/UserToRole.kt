package com.between_freedom_and_space.mono_backend.access.entities.role.models

import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserToRolesTable
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

class UserToRole(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<UserToRole>(UserToRolesTable)

    var role by UserToRolesTable.role

    var user by UserToRolesTable.user

    var createdDate by UserToRolesTable.createdDate

    var updatedDate by UserToRolesTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserToRole

        if (id.value != other.id.value) return false
        if (role != other.role) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = role.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + user.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}