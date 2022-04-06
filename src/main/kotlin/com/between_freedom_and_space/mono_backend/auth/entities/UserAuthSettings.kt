package com.between_freedom_and_space.mono_backend.auth.entities

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserAuthSettings(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<UserAuthSettings>(UserAuthSettingsTable)

    var accessTokenLifetime by UserAuthSettingsTable.accessTokenLifetime

    var refreshTokenLifetime by UserAuthSettingsTable.refreshTokenLifetime
}