package com.cj.smartworker.business.fcm.service

import com.cj.smartworker.business.fcm.port.`in`.EmergencyAlarmPushUseCase
import com.cj.smartworker.business.fcm.port.out.FcmPushPort
import com.cj.smartworker.business.fcm.port.out.FindTokenPort
import com.cj.smartworker.business.fcm.port.out.SaveFcmHistoryPort
import com.cj.smartworker.business.member.port.out.FindAdminPort
import com.cj.smartworker.domain.fcm.valueobject.Emergency
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.EmployeeName
import com.cj.smartworker.domain.util.logger
import com.cj.smartworker.domain.util.toKstLocalDateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.LocalDate
import java.time.Period

@Service
internal class EmergencyAlarmPushService(
    private val fcmPushPort: FcmPushPort,
    private val findTokenPort: FindTokenPort,
    private val findAdminPort: FindAdminPort,
    private val saveFcmHistoryPort: SaveFcmHistoryPort,
) : EmergencyAlarmPushUseCase {

    companion object {
        private const val HEART_RATE = "심박수 신고 알림"
        private const val REPORT = "신고 알림"
    }

    private val logger = logger()

    /**
     * 긴급 신고 알림을 푸시합니다.
     * @param member 신고한 회원
     * @return Unit
     */
    @Transactional
    override fun pushEmergencyAlarm(
        member: Member,
        x: Float,
        y: Float,
        emergency: Emergency,
    ): List<EmployeeName> {
        val findAdminIds = findAdminPort.findAdmins()
        val admins = mutableSetOf<Member>()
        val createdAt = Instant.now().toKstLocalDateTime()
        val title = if (emergency == Emergency.HEART_RATE) HEART_RATE else REPORT
        val date = LocalDate.of(member.year.year, member.month.month, member.day.day)
        val age = Period.between(date, LocalDate.now()).years
        if (emergency == Emergency.HEART_RATE) {
            findTokenPort.findByMemberId(member.memberId!!)?.let {
                fcmPushPort.sendMessage(
                    targetToken = it.token.token,
                    title = title,
                    body = "심박수가 높습니다. 확인해주세요.",
                    x = x,
                    y = y,
                    age = age,
                    employeeName = member.employeeName,
                    phone = member.phone,
                    createdAt = createdAt,
                )
            }
        }
        findTokenPort.findByMemberIds(findAdminIds.map { it.memberId!! }).forEach {
            val sendMessageSuccess = fcmPushPort.sendMessage(
                targetToken = it.key.token,
                title = title,
                body = "${member.employeeName.employeeName}님께서 긴급 신고를 하였습니다. 확인해주세요.",
                x = x,
                y = y,
                age = age,
                employeeName = member.employeeName,
                phone = member.phone,
                createdAt = createdAt,
            )
            if (sendMessageSuccess) {
                admins.add(it.value)
            }
        }
        if (admins.isEmpty()) {
            throw RuntimeException("긴급 신고 알림을 보낼 수 있는 관리자가 없습니다.")
        }
        saveFcmHistoryPort.saveFcmHistory(
            reporter = member,
            admins = admins,
            createdAt = Instant.now().toKstLocalDateTime(),
            x = x,
            y = y,
            emergency = emergency,
        )
        admins.forEach {
            logger.info("긴급 신고 알림을 받은 관리자: ${it.employeeName}")
        }
        return admins.map { it.employeeName }
    }
}
