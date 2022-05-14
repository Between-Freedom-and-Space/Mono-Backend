package com.between_freedom_and_space.mono_backend.profiles.repository

import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserSubscription

interface CommonSubscriptionsRepository {

    fun subscribeProfile(authorProfile: UserProfile, targetProfile: UserProfile): UserSubscription?

    fun unsubscribeProfile(authorProfile: UserProfile, targetProfile: UserProfile): UserSubscription?
}