package com.between_freedom_and_space.mono_backend.auth.security.models

data class UserAuthority(
    val userId: String,
    val userName: String,
) {

    companion object {

        const val USER_ID_ALIAS = "user_id"
        const val USER_NAME_ALIAS = "user_name"
    }
}
