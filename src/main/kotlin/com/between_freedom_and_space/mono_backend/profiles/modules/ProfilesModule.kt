package com.between_freedom_and_space.mono_backend.profiles.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.mappers.BaseProfileModelToProfileModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.mappers.CreateProfileRequestToCreateModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.mappers.SubscribeActionResultToSubscribeResponseMapper
import com.between_freedom_and_space.mono_backend.profiles.api.mappers.UpdateProfileRequestToUpdateModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.models.CreateProfileRequest
import com.between_freedom_and_space.mono_backend.profiles.api.models.ProfileModel
import com.between_freedom_and_space.mono_backend.profiles.api.models.SubscribeActionResponse
import com.between_freedom_and_space.mono_backend.profiles.api.models.UpdateProfileRequest
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonSubscriptionsRepository
import com.between_freedom_and_space.mono_backend.profiles.repository.impl.CommonProfilesRepositoryImpl
import com.between_freedom_and_space.mono_backend.profiles.repository.impl.CommonSubscriptionsRepositoryImpl
import com.between_freedom_and_space.mono_backend.profiles.services.ActionProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.InformationProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.InteractionProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.impl.ActionProfileServiceImpl
import com.between_freedom_and_space.mono_backend.profiles.services.impl.InformationProfileServiceImpl
import com.between_freedom_and_space.mono_backend.profiles.services.impl.InteractionProfileServiceImpl
import com.between_freedom_and_space.mono_backend.profiles.services.mappers.UserProfileToBaseProfileModelMapper
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.SubscribeActionResult
import com.between_freedom_and_space.mono_backend.profiles.services.models.UpdateProfileModel
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<BaseProfileModel, ProfileModel>> { BaseProfileModelToProfileModelMapper() }
    single<ModelMapper<CreateProfileRequest, CreateProfileModel>> { CreateProfileRequestToCreateModelMapper() }
    single<ModelMapper<SubscribeActionResult, SubscribeActionResponse>> { SubscribeActionResultToSubscribeResponseMapper() }
    single<ModelMapper<UpdateProfileRequest, UpdateProfileModel>> { UpdateProfileRequestToUpdateModelMapper() }
    single<ModelMapper<UserProfile, BaseProfileModel>> { UserProfileToBaseProfileModelMapper() }
}

private val repositoryModule = module {
    single { CommonProfilesRepositoryImpl() } bind CommonProfilesRepository::class
    single { CommonSubscriptionsRepositoryImpl() } bind CommonSubscriptionsRepository::class
}

private val serviceModule = module {
    single { InformationProfileServiceImpl(get(), get(), get(), get(), get(), get(), get()) } bind InformationProfilesService::class
    single { ActionProfileServiceImpl(get(), get()) } bind ActionProfilesService::class
    single { InteractionProfileServiceImpl(get(), get(), get()) } bind InteractionProfilesService::class
}

val profilesModule = module {
    includes(mappersModule)
    includes(repositoryModule)
    includes(serviceModule)
}