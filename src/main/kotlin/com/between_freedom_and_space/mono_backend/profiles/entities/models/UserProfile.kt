package com.between_freedom_and_space.mono_backend.profiles.entities.models

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.entities.post.PostsTable
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostCommentsTable
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserSubscriptionsTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

class UserProfile(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<UserProfile>(UserProfilesTable)

    var mail by UserProfilesTable.mail

    var phoneNumber by UserProfilesTable.phoneNumber

    var passwordEncrypted by UserProfilesTable.passwordEncrypted

    var nickName by UserProfilesTable.nickName

    var nameAlias by UserProfilesTable.nameAlias

    var description by UserProfilesTable.description

    var location by UserProfilesTable.location

    var isVisible by UserProfilesTable.isVisible

    var isDeleted by UserProfilesTable.isDeleted

    val post by Post referrersOn PostsTable.author

    val comments by PostComment referrersOn PostCommentsTable.author

    val subscribers by UserSubscription referrersOn UserSubscriptionsTable.subscribeOn

    val subscriptions by UserSubscription referrersOn UserSubscriptionsTable.user

    var createdDate by UserProfilesTable.createdDate

    var updatedDate by UserProfilesTable.updatedDate

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
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mail.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + passwordEncrypted.hashCode()
        result = 31 * result + nickName.hashCode()
        result = 31 * result + nameAlias.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}
