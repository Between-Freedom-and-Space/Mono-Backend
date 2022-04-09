package com.between_freedom_and_space.mono_backend.util.module

import com.between_freedom_and_space.mono_backend.util.components.RequestValidator
import com.between_freedom_and_space.mono_backend.util.components.impl.HibernateRequestValidator
import org.koin.dsl.bind
import org.koin.dsl.module

val utilModule = module {
    single { HibernateRequestValidator() } bind RequestValidator::class
}