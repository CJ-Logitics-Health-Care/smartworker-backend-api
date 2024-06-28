package com.cj.smartworker.business.member.port.out

import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.Phone

fun interface SendApprovalCodeToPhonePort {
    fun sendApprovalCodeToPhone(phone: Phone, code: ApprovalCode)
}
