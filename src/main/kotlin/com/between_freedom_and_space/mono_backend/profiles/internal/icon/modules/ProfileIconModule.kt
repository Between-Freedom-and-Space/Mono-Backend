package com.between_freedom_and_space.mono_backend.profiles.internal.icon.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.mappers.BaseProfileModelToProfileModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.mappers.BaseIconModelToModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.mappers.CreateProfileIconRequestToModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.mappers.UpdateProfileIconRequestToModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.CreateProfileIconRequest
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.ProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.UpdateProfileIconRequest
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.entities.ProfileIcon
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.modules.qualifiers.ProfileIconMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.CommonProfileIconRepository
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.impl.CommonProfileIconRepositoryImpl
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.model.CreateProfileIconEntityModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.InformationProfileIconService
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.InteractionProfileIconService
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.impl.InformationProfileIconServiceImpl
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.impl.InteractionProfileIconServiceImpl
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.mappers.CreateProfileIconModelToCreateEntityMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.mappers.ProfileIconToBaseModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.CreateProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.UpdateProfileIconModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<BaseProfileIconModel, ProfileIconModel>>(
        named(ProfileIconMappersQualifiers.BASE_PROFILE_ICON_MODEL_TO_MODEL_MAPPER)
    ) { BaseIconModelToModelMapper() }
    single<ModelMapper<CreateProfileIconRequest, CreateProfileIconModel>>(
        named(ProfileIconMappersQualifiers.CREATE_PROFILE_ICON_REQUEST_TO_MODEL_MAPPER)
    ) { CreateProfileIconRequestToModelMapper() }
    single<ModelMapper<UpdateProfileIconRequest, UpdateProfileIconModel>>(
        named(ProfileIconMappersQualifiers.UPDATE_PROFILE_ICON_REQUEST_TO_MODEL_MAPPER)
    ) { UpdateProfileIconRequestToModelMapper() }
    single<ModelMapper<ProfileIcon, BaseProfileIconModel>>(
        named(ProfileIconMappersQualifiers.PROFILE_ICON_TO_BASE_MODEL_MAPPER)
    ) { ProfileIconToBaseModelMapper() }
    single<ModelMapper<CreateProfileIconModel, CreateProfileIconEntityModel>>(
        named(ProfileIconMappersQualifiers.CREATE_PROFILE_ICON_MODEL_TO_CREATE_ENTITY_MAPPER)
    ) { CreateProfileIconModelToCreateEntityMapper() }
}

private val repositoryModule = module {
    single { CommonProfileIconRepositoryImpl() } bind CommonProfileIconRepository::class
}

private val serviceModule = module {
    single { InformationProfileIconServiceImpl(
        get(),
        get(named(ProfileIconMappersQualifiers.PROFILE_ICON_TO_BASE_MODEL_MAPPER))
    ) } bind InformationProfileIconService::class
    single { InteractionProfileIconServiceImpl(
        get(), get(),
        get(named(ProfileIconMappersQualifiers.PROFILE_ICON_TO_BASE_MODEL_MAPPER)),
        get(named(ProfileIconMappersQualifiers.CREATE_PROFILE_ICON_MODEL_TO_CREATE_ENTITY_MAPPER))
    ) } bind InteractionProfileIconService::class
}

val profileIconModule = module {
    includes(mappersModule)
    includes(repositoryModule)
    includes(serviceModule)
}