package com.between_freedom_and_space.mono_backend.profiles.modules

import com.between_freedom_and_space.mono_backend.profiles.services.InformationProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.impl.InformationProfileServiceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {

}

private val repositoryModule = module {

}

private val serviceModule = module {
    single { InformationProfileServiceImpl(get()) } bind InformationProfilesService::class
}

val profilesModule = module {
    includes(mappersModule)
    includes(repositoryModule)
    includes(serviceModule)
}