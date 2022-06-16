package com.between_freedom_and_space.mono_backend.common.plugins

import com.between_freedom_and_space.mono_backend.common.plugins.impl.DefaultExceptionInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module

internal val exceptionHandlerPluginModule = module {
    single { DefaultExceptionInterceptor() } bind ExceptionInterceptor::class
}