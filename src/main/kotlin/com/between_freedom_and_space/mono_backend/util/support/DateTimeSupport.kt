package com.between_freedom_and_space.mono_backend.util.support

import com.between_freedom_and_space.mono_backend.common.constants.DateTimeConstants
import com.between_freedom_and_space.mono_backend.common.constants.DateTimeConstants.DEFAULT_TIMEZONE
import kotlinx.datetime.*
import kotlinx.datetime.DateTimeUnit.Companion.MILLISECOND

fun localDateTimeNow(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(DEFAULT_TIMEZONE)
}

fun localDateTimeNowPlus(millis: Long): LocalDateTime {
    val instant = Clock.System.now().plus(millis, MILLISECOND, DEFAULT_TIMEZONE)
    return instant.toLocalDateTime(DEFAULT_TIMEZONE)
}