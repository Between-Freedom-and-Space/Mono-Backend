package com.between_freedom_and_space.mono_backend.common

interface ModelMapper <IN: Any, OUT: Any> {

    fun map(original: IN): OUT
}