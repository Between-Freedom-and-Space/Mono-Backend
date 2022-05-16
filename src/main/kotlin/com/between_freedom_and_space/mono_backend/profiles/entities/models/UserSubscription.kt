package com.between_freedom_and_space.mono_backend.profiles.entities.models

import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserSubscriptionsTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserSubscription(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<UserSubscription>(UserSubscriptionsTable)

    var user by UserSubscriptionsTable.user

    var subscribeOn by UserSubscriptionsTable.subscribeOn

    var createdDate by UserSubscriptionsTable.createdDate

    var updatedDate by UserSubscriptionsTable.updatedDate
}