package com.between_freedom_and_space.mono_backend.auth.entities

import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import org.jetbrains.exposed.sql.kotlin.datetime.duration

object UserAuthSettingsTable: LongIdTable("user_auth_settings") {

    val targetUser = reference(
        name = "user_id", foreign = UserProfilesTable,
        onUpdate = NO_ACTION, onDelete = NO_ACTION
    )

    val accessTokenLifetime = duration("access_token_lifetime")

    val refreshTokenLifetime = duration("refresh_token_lifetime")

    val createdDate = datetime("created_date").clientDefault { localDateTimeNow() }

    val updatedDate = datetime("updated_date").clientDefault { localDateTimeNow() }
}