package com.cj.smartworker.business.fcm.port.`in`

import com.cj.smartworker.domain.fcm.valueobject.Emergency
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.EmployeeName

fun interface EmergencyAlarmPushUseCase {
    fun pushEmergencyAlarm(
        member: Member,
        x: Int,
        y: Int,
        emergency: Emergency,
    ): List<EmployeeName>
}
