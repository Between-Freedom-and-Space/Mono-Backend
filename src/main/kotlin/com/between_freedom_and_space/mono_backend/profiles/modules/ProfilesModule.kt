package com.between_freedom_and_space.mono_backend.profiles.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.models.UserProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.CommonProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.impl.CommonProfileServiceImpl
import com.between_freedom_and_space.mono_backend.profiles.services.mappers.UserProfileToUserProfileModelMapper
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<UserProfile, UserProfileModel>> { UserProfileToUserProfileModelMapper() }
}

private val repositoryModule = module {

}

private val serviceModule = module {
    single { CommonProfileServiceImpl(get(), get()) } bind CommonProfilesService::class
}

val profilesModule = module {
    includes(mappersModule)
    includes(repositoryModule)
    includes(serviceModule)
}