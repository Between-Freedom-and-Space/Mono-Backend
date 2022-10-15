package com.between_freedom_and_space.mono_backend.mailing.api.validators

import com.between_freedom_and_space.mono_backend.mailing.api.validators.impl.TelephoneNumberValidator
import javax.validation.Constraint

@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_GETTER
)
@Constraint(validatedBy = [ TelephoneNumberValidator::class ])
annotation class TelephoneNumber(

    val message: String = "Invalid Telephone number",
)
