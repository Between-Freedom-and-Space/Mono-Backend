package com.between_freedom_and_space.mono_backend.profiles.services.models

data class SubscribeActionResult(

    val result: ActionResult,

    val message: String,
) {

    enum class ActionResult {
        SUCCESS,
        FAILURE;
    }
}
