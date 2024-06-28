package com.cj.smartworker.business.member.port.out

import com.cj.smartworker.domain.member.valueobject.Phone

fun interface DeleteApprovalCodePort {
    fun deleteApprovalCode(phone: Phone)
}
