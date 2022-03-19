package com.between_freedom_and_space.mono_backend.profiles.entities

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserProfile(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<UserProfile>(UserProfilesTable)

    var mail by UserProfilesTable.mail

    var passwordEncrypted by UserProfilesTable.passwordEncrypted

    var nickName by UserProfilesTable.nickName

    var nameAlias by UserProfilesTable.nameAlias

    var description by UserProfilesTable.description

    var locataion by UserProfilesTable.location

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserProfile

        if (id.value != other.id.value) return false
        if (mail != other.mail) return false
        if (passwordEncrypted != other.passwordEncrypted) return false
        if (nickName != other.nickName) return false
        if (nameAlias != other.nameAlias) return false
        if (description != other.description) return false
        if (locataion != other.locataion) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mail.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + passwordEncrypted.hashCode()
        result = 31 * result + nickName.hashCode()
        result = 31 * result + nameAlias.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + locataion.hashCode()
        return result
    }

}
