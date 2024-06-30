package com.cj.smartworker.business.member.port.`in`

import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.Phone

fun interface ApproveCodeUseCase {
    /**
     * 승인 코드가 일치하면 true, 일치하지 않으면 false를 반환한다.
     */
    fun approve(
        approvalCode: ApprovalCode,
        phone: Phone,
    ): Boolean
}
