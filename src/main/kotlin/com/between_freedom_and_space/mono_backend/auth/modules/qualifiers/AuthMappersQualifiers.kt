package com.between_freedom_and_space.mono_backend.auth.modules.qualifiers

internal enum class AuthModelMapperQualifier {
    TOKEN_VERIFY_RESULT_TO_RESPONSE,
    BASE_PROFILE_MODEL_TO_REGISTER_USER_RESPONSE,
    PRODUCER_RESULT_TO_AUTHENTICATE_RESPONSE,
    REGISTER_USER_REQUEST_TO_MODEL,
    USER_PROFILE_TO_AUTH_MODEL;
}