package com.between_freedom_and_space.mono_backend.profiles.internal.icon.entities

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

class ProfileIcon(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<ProfileIcon>(ProfileIconsTable)

    var iconBase64 by ProfileIconsTable.iconBase64

    var profile by ProfileIconsTable.profile

    var createdDate by ProfileIconsTable.createdDate

    var updatedDate by ProfileIconsTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProfileIcon

        if (iconBase64 != other.iconBase64) return false
        if (profile != other.profile) return false

        return true
    }

    override fun hashCode(): Int {
        var result = iconBase64.hashCode()
        result = 31 * result + profile.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}