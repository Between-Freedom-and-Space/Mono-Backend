package com.between_freedom_and_space.mono_backend.auth.modules

import com.between_freedom_and_space.mono_backend.auth.security.JWTCreator
import com.between_freedom_and_space.mono_backend.auth.security.JWTVerifier
import com.between_freedom_and_space.mono_backend.auth.security.impl.HS256Creator
import com.between_freedom_and_space.mono_backend.auth.security.impl.HS256Verifier
import org.koin.dsl.bind
import org.koin.dsl.module

private val securityModule = module {
    single { HS256Creator() } bind JWTCreator::class
    single { HS256Verifier() } bind JWTVerifier::class
}

val authModule = module {
    plus(securityModule)
}