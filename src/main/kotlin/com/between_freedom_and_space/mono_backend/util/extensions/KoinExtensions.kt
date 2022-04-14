package com.between_freedom_and_space.mono_backend.util.extensions

import org.koin.java.KoinJavaComponent

inline fun <reified T> inject(): Lazy<T> {
    return KoinJavaComponent.inject(T::class.java)
}