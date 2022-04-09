package com.between_freedom_and_space.mono_backend.util.extensions

inline fun <reified T> T?.ifNull(action: () -> Unit): T? {
    if (this == null) {
        action.invoke()
    }
    return this
}

inline fun <reified T> T?.ifNotNull(action: (T) -> Unit): T? {
    if (this != null) {
        action.invoke(this)
    }
    return this
}