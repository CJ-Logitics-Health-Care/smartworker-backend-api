package com.cj.smartworker.business.member.service

import com.cj.smartworker.business.member.port.`in`.ApproveCodeUseCase
import com.cj.smartworker.business.member.port.out.FindApprovalCodePort
import com.cj.smartworker.business.member.port.out.SavePhoneApprovalCodePort
import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.Phone
import com.cj.smartworker.domain.util.toKstLocalDateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
internal class ApproveCodeService(
    private val findApprovalCodePort: FindApprovalCodePort,
    private val savePhoneApprovalCodePort: SavePhoneApprovalCodePort,
): ApproveCodeUseCase {

    @Transactional
    override fun approve(
        approvalCode: ApprovalCode,
        phone: Phone,
    ): Boolean {
        val phoneApproval = findApprovalCodePort.findByPhone(phone) ?: run {
            throw IllegalArgumentException("인증번호가 존재하지 않습니다. \n다시 인증번호를 요청해 주세요.")
        }
        phoneApproval.createdAt.isBefore(Instant.now().toKstLocalDateTime().minusMinutes(5)).let {
                if (it) {
                    throw IllegalArgumentException("인증번호가 만료되었습니다. \n다시 인증번호를 요청해 주세요.")
                }
            }
        if (phoneApproval.approvalCode.approvalCode != approvalCode.approvalCode) {
            throw IllegalArgumentException("인증번호가 일치하지 않습니다.")
        }

        val approved = phoneApproval.approve()
        savePhoneApprovalCodePort.savePhoneApprovalCode(approved)
        return true
    }
}
