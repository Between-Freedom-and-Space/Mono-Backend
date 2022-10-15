package com.between_freedom_and_space.mono_backend.mailing.service.models

sealed class VerificationCheckResult {

    object Valid: VerificationCheckResult()

    object Invalid: VerificationCheckResult()

    object NotFound: VerificationCheckResult()
}
