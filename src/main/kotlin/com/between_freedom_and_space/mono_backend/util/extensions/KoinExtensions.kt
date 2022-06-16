package com.between_freedom_and_space.mono_backend.util.extensions

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.qualifier
import org.koin.java.KoinJavaComponent

inline fun <reified T> inject(): Lazy<T> {
    return KoinJavaComponent.inject(T::class.java)
}

inline fun <reified T: Any> inject(qualifier: Qualifier): Lazy<T> {
    return KoinJavaComponent.inject(T::class.java, qualifier)
}

inline fun <reified T: Any, reified E : Enum<E>> inject(named: Enum<E>): Lazy<T> {
    val qualifier = named.qualifier
    return KoinJavaComponent.inject(T::class.java, qualifier)
}
