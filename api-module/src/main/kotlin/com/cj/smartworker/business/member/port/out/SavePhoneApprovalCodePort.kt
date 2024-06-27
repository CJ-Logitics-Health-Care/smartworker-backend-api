package com.cj.smartworker.business.member.port.out

import com.cj.smartworker.domain.member.entity.PhoneApproval

fun interface SavePhoneApprovalCodePort {
    fun savePhoneApprovalCode(phoneApproval: PhoneApproval): PhoneApproval
}
