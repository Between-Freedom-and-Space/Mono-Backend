package com.between_freedom_and_space.mono_backend.util.components

import javax.validation.ConstraintViolation

interface RequestValidator {

    fun <T> validateRequest(request: T): Set<ConstraintViolation<T>>
}