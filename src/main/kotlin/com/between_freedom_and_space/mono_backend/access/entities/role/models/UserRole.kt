package com.between_freedom_and_space.mono_backend.access.entities.role.models

import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserRolesTable
import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserToRolesTable
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserRole(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<UserRole>(UserRolesTable)

    var roleAlias by UserRolesTable.roleAlias

    val users by UserToRole referrersOn UserToRolesTable.role

    var createdDate by UserRolesTable.createdDate

    var updatedDate by UserRolesTable.updatedDate

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}