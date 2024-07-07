package com.cj.smartworker.business.fcm.port.out

import com.cj.smartworker.domain.member.entity.Member
import java.time.LocalDateTime

fun interface SaveFcmHistoryPort {
    fun saveFcmHistory(
        reporter: Member,
        admins: Set<Member>,
        createdAt: LocalDateTime,
    )
}
