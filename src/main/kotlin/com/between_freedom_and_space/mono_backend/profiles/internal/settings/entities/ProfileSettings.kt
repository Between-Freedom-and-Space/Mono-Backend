package com.between_freedom_and_space.mono_backend.profiles.internal.settings.entities

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProfileSettings(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<ProfileSettings>(ProfileSettingsTable)

    var isClosedProfile by ProfileSettingsTable.isClosedProfile
}