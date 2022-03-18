package com.between_freedom_and_space.mono_backend.profiles.entities

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserProfile: Table("user_profiles") {

    val id = long("id").autoIncrement().entityId()

    val mail = text("mail")

    val passwordEncrypted = text("password_encrypted")

    val nickName = text("nick_name")

    val nameAlias = text("name_alias")

    val description = text("description")

    val location = text("location")

    val createdDate = datetime("created_date")

    val modifiedDate = datetime("modified_date")
}