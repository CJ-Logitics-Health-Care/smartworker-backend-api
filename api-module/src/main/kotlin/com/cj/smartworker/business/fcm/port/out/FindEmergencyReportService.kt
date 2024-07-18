package com.cj.smartworker.business.fcm.port.out

import com.cj.smartworker.business.fcm.dto.response.EmergencyReportDto
import com.cj.smartworker.business.fcm.port.`in`.FindEmergencyReportUseCase
import com.cj.smartworker.business.member.port.out.FindMemberPort
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.MemberId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
internal class FindEmergencyReportService(
    private val findEmergencyReportPort: FindEmergencyReportPort,
    private val findMemberPort: FindMemberPort,
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

    override fun findReport(memberId: MemberId): List<EmergencyReportDto> {
        val member: Member = findMemberPort.findById(memberId) ?: run {
            throw IllegalArgumentException("해당 회원을 찾지 못했습니다.")
        }
        return findEmergencyReportPort.findReport(member)
    }
}
