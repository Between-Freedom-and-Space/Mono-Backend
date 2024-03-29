package com.between_freedom_and_space.mono_backend.profiles.entities.tables

import com.between_freedom_and_space.mono_backend.profiles.internal.icon.entities.ProfileIconsTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserProfilesTable: LongIdTable("user_profiles") {

    val mail = text("mail", eagerLoading = true).uniqueIndex().nullable()

    val phoneNumber = text("phone_number", eagerLoading = true).uniqueIndex().nullable()

    val passwordEncrypted = text("password_encrypted", eagerLoading = true)

    val nickName = text("nick_name", eagerLoading = true).uniqueIndex()

    val nameAlias = text("name_alias", eagerLoading = true)

    val description = text("description", eagerLoading = true).nullable()

    val location = text("location", eagerLoading = true).nullable()

    val icon = reference(
        "profile_icon_id", foreign = ProfileIconsTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    ).nullable()

    val isDeleted = bool("is_deleted").default(false)

    val isVisible = bool("is_visible").default(true)

    val createdDate = datetime("created_date").clientDefault { localDateTimeNow() }

    val updatedDate = datetime("updated_date").clientDefault { localDateTimeNow() }
}