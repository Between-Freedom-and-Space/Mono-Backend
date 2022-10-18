package com.between_freedom_and_space.mono_backend.util.support

import com.between_freedom_and_space.mono_backend.common.constants.DateTimeConstants.DEFAULT_TIMEZONE
import kotlinx.datetime.*
import kotlinx.datetime.DateTimeUnit.Companion.MILLISECOND
import java.util.*

fun localDateTimeNow(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(DEFAULT_TIMEZONE)
}

fun localDateTimeNowPlus(millis: Long): LocalDateTime {
    val instant = Clock.System.now().plus(millis, MILLISECOND, DEFAULT_TIMEZONE)
    return instant.toLocalDateTime(DEFAULT_TIMEZONE)
}

fun localDateTimeNowMinus(millis: Long): LocalDateTime {
    val instant = Clock.System.now().minus(millis, MILLISECOND, DEFAULT_TIMEZONE)
    return instant.toLocalDateTime(DEFAULT_TIMEZONE)
}

fun dateNow(): Date {
    return Date.from(localDateTimeNow().toInstant(DEFAULT_TIMEZONE).toJavaInstant())
}