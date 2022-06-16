package com.between_freedom_and_space.mono_backend.access.modules

import org.koin.dsl.module

private val mappersModule = module {

}

private val repositoryModule = module {

}

private val serviceModule = module {

}

val accessModule = module {
    includes(mappersModule)
    includes(repositoryModule)
    includes(serviceModule)
}