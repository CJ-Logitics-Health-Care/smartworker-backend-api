package com.cj.smartworker.business.member.port.`in`

import com.cj.smartworker.domain.member.valueobject.Phone

fun interface SendPhoneApprovalCodeUseCase {

    /**
     * 전화번호 인증 코드를 반환한다.
     */

    fun requestPhoneApproveCode(phone: Phone): String
}
