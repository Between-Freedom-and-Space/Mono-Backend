package com.between_freedom_and_space.mono_backend.util.extensions

import com.between_freedom_and_space.mono_backend.common.constants.DateTimeConstants.DEFAULT_TIMEZONE
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(DEFAULT_TIMEZONE)
}