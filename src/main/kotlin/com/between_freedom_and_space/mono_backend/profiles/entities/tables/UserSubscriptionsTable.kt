package com.between_freedom_and_space.mono_backend.profiles.entities.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserSubscriptionsTable: LongIdTable("user_subscriptions") {

    val user = reference(
        name = "user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val subscribeOn = reference(
        name = "user_subscribe_on_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val isDeleted = bool("is_deleted").default(false)

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}