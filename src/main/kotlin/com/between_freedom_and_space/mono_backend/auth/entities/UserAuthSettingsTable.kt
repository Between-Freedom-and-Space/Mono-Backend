package com.between_freedom_and_space.mono_backend.auth.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import org.jetbrains.exposed.sql.kotlin.datetime.duration

object UserAuthSettingsTable: LongIdTable("user_auth_settings") {

    val accessTokenLifetime = duration("access_token_lifetime")

    val refreshTokenLifetime = duration("refresh_token_lifetime")

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}