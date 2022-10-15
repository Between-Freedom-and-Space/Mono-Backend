package com.between_freedom_and_space.mono_backend.mailing.api.validators.impl

import com.between_freedom_and_space.mono_backend.mailing.api.validators.TelephoneNumber
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class TelephoneNumberValidator: ConstraintValidator<TelephoneNumber, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrBlank()) {
            return false
        }
        return true
    }
}