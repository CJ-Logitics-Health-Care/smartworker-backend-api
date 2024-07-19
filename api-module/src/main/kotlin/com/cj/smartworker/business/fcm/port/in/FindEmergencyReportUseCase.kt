package com.cj.smartworker.business.fcm.port.`in`

import com.cj.smartworker.business.fcm.dto.response.EmergencyReportDto
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.LoginId
import com.cj.smartworker.domain.member.valueobject.MemberId
import java.time.LocalDateTime

interface FindEmergencyReportUseCase {
    fun findReport(start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto>

    fun findReport(member: Member, start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto>

    fun findReport(memberId: MemberId, start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto>

    fun findReport(loginId1: LoginId): List<EmergencyReportDto>
    fun findReport(loginId1: LoginId, start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto>
}
