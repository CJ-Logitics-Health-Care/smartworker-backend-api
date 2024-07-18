package com.cj.smartworker.business.fcm.port.out

import com.cj.smartworker.business.fcm.dto.response.EmergencyReportDto
import com.cj.smartworker.business.fcm.port.`in`.FindEmergencyReportUseCase
import com.cj.smartworker.domain.member.entity.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
internal class FindEmergencyReportService(
    private val findEmergencyReportPort: FindEmergencyReportPort,
): FindEmergencyReportUseCase {

    @Transactional(readOnly = true)
    override fun findReport(start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto> {
        return findEmergencyReportPort.findReport(
            start = start,
            end = end,
        )
    }

    override fun findReport(member: Member): List<EmergencyReportDto> {
        return findEmergencyReportPort.findReport(member)
    }
}
