package com.between_freedom_and_space.mono_backend.profiles.repository.impl

import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserSubscription
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserSubscriptionsTable
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonSubscriptionsRepository
import com.between_freedom_and_space.mono_backend.profiles.repository.exceptions.ProfileSubscriptionException
import com.between_freedom_and_space.mono_backend.util.extensions.ifNotNull
import com.between_freedom_and_space.mono_backend.util.extensions.ifNull
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select

class CommonSubscriptionsRepositoryImpl: CommonSubscriptionsRepository {

    override fun subscribeProfile(authorProfile: UserProfile, targetProfile: UserProfile): UserSubscription? {
        val authorId = authorProfile.id
        val targetId = targetProfile.id

        UserSubscriptionsTable
            .select {
                val firstExpression = (UserSubscriptionsTable.user eq authorId)
                val secondExpression = (UserSubscriptionsTable.subscribeOn eq targetId)
                firstExpression and secondExpression
            }
            .firstOrNull()
            .ifNotNull { return null }

        return UserSubscription.new {
            user = authorId
            subscribeOn = targetId
        }
    }

    override fun unsubscribeProfile(authorProfile: UserProfile, targetProfile: UserProfile): UserSubscription? {
        val authorId = authorProfile.id
        val targetId = targetProfile.id

        val subscriptionRow = UserSubscriptionsTable
            .select {
                val firstExpression = (UserSubscriptionsTable.user eq authorId)
                val secondExpression = (UserSubscriptionsTable.subscribeOn eq targetId)
                firstExpression and secondExpression
            }
            .firstOrNull() ?: return null

        val subscription = UserSubscription.wrapRow(subscriptionRow)
        subscription.delete()
        return subscription
    }
}