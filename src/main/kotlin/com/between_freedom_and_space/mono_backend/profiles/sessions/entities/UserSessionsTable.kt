package com.between_freedom_and_space.mono_backend.profiles.sessions.entities

import org.jetbrains.exposed.sql.Table

object UserSessionsTable: Table("user_sessions") {

    val id = long("id").autoIncrement().entityId()
}