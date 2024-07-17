package com.cj.smartworker.business.fcm.port.`in`

import com.cj.smartworker.business.fcm.dto.response.EmergencyReportDto
import java.time.LocalDateTime

fun interface FindEmergencyReportUseCase {
    fun findReport(start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto>
}
