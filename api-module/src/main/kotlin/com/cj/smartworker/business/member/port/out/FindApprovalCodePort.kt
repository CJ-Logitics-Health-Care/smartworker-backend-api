package com.cj.smartworker.business.member.port.out

import com.cj.smartworker.domain.member.entity.PhoneApproval
import com.cj.smartworker.domain.member.valueobject.Phone

fun interface FindApprovalCodePort {
    /**
     * 가장 최근의 phone 인증번호를 반환한다.
     */
    fun findByPhone(phone: Phone): PhoneApproval?
}
