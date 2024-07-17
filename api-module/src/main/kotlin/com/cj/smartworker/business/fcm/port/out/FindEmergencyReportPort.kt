package com.cj.smartworker.business.fcm.port.out

import com.cj.smartworker.business.fcm.dto.response.EmergencyReportDto
import java.time.LocalDateTime

interface FindEmergencyReportPort {
    fun findReport(start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto>
}
