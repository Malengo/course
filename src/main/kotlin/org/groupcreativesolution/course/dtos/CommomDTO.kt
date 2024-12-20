package org.groupcreativesolution.course.dtos

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

open class CommomDTO {
    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        val now: String = LocalDateTime.now(ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS).format(formatter)
    }
}