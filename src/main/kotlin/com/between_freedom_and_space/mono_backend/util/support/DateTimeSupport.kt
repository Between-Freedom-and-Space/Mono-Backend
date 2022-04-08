package com.between_freedom_and_space.mono_backend.util.support

import com.between_freedom_and_space.mono_backend.common.constants.DateTimeConstants
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun localDateTimeNow(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(DateTimeConstants.DEFAULT_TIMEZONE)
}