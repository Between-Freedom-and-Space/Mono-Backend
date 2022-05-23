package com.between_freedom_and_space.mono_backend.util.components.impl

import com.between_freedom_and_space.mono_backend.util.components.RequestValidator
import javax.validation.ConstraintViolation
import javax.validation.Validation

class HibernateRequestValidator: RequestValidator {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    override fun <T> validateRequest(request: T): Set<ConstraintViolation<T>> {
        return validator.validate(request).also {
            val f = it
        }
    }
}