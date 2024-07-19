package com.cj.smartworker.business.fcm.port.out

import com.cj.smartworker.business.fcm.dto.response.EmergencyReportDto
import com.cj.smartworker.business.fcm.port.`in`.FindEmergencyReportUseCase
import com.cj.smartworker.business.member.port.out.FindMemberPort
import com.cj.smartworker.business.member.port.out.SearchMemberPort
import com.cj.smartworker.business.member.util.MaskingUtil
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.EmployeeName
import com.cj.smartworker.domain.member.valueobject.LoginId
import com.cj.smartworker.domain.member.valueobject.MemberId
import com.cj.smartworker.domain.member.valueobject.Phone
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
internal class FindEmergencyReportService(
    private val findEmergencyReportPort: FindEmergencyReportPort,
    private val findMemberPort: FindMemberPort,
    private val searchMemberPort: SearchMemberPort
): FindEmergencyReportUseCase {

    @Transactional(readOnly = true)
    override fun findReport(start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto> {
        val reportList = findEmergencyReportPort.findReport(
            start = start,
            end = end,
        )
        reportList.forEach { report ->
            report.apply {
                reporter = MaskingUtil.maskEmployeeName(EmployeeName(reporter))
                phone = MaskingUtil.maskPhone(Phone(phone))
                loginId = MaskingUtil.maskLoginId(LoginId(loginId))
            }
        }
        return reportList
    }

    @Transactional(readOnly = true)
    override fun findReport(member: Member, start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto> {
        return findEmergencyReportPort.findReport(
            member = member,
            start = start,
            end = end,
        )
    }

    /**
     * Admin이 특정 회원의 신고 내역 조회
     */
    @Transactional(readOnly = true)
    override fun findReport(memberId: MemberId, start: LocalDateTime, end: LocalDateTime): List<EmergencyReportDto> {
        val member: Member = findMemberPort.findById(memberId) ?: run {
            throw IllegalArgumentException("해당 회원을 찾지 못했습니다.")
        }
        return findEmergencyReportPort.findReport(
            member = member,
            start = start,
            end = end,
        )
    }

    override fun findReport(loginId1: LoginId): List<EmergencyReportDto> {
        val member = searchMemberPort.searchByLoginIdReturnMember(loginId1) ?: run {
            return listOf()
        }
        val reportList = findEmergencyReportPort.findReport(member)
        reportList.forEach { report ->
            report.apply {
                reporter = MaskingUtil.maskEmployeeName(EmployeeName(reporter))
                phone = MaskingUtil.maskPhone(Phone(phone))
                loginId = MaskingUtil.maskLoginId(LoginId(loginId))
            }
        }
        return reportList
    }

    override fun findReport(
        loginId1: LoginId,
        start: LocalDateTime,
        end: LocalDateTime
    ): List<EmergencyReportDto> {
        val member = searchMemberPort.searchByLoginIdReturnMember(loginId1) ?: run {
            return listOf()
        }
        val reportList = findEmergencyReportPort.findReport(
            member = member,
            start = start,
            end = end,
        )
        reportList.forEach { report ->
            report.apply {
                reporter = MaskingUtil.maskEmployeeName(EmployeeName(reporter))
                phone = MaskingUtil.maskPhone(Phone(phone))
                loginId = MaskingUtil.maskLoginId(LoginId(loginId))
            }
        }
        return reportList
    }
}
