package com.between_freedom_and_space.mono_backend.profiles.entities.models

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserSubscriptionsTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

class UserSubscription(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<UserSubscription>(UserSubscriptionsTable)

    var user by UserSubscriptionsTable.user

    var subscribeOn by UserSubscriptionsTable.subscribeOn

    var createdDate by UserSubscriptionsTable.createdDate

    var updatedDate by UserSubscriptionsTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserSubscription

        if (id.value != other.id.value) return false
        if (user != other.user) return false
        if (subscribeOn != other.subscribeOn) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + subscribeOn.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}