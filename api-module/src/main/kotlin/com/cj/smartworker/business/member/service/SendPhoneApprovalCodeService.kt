package com.cj.smartworker.business.member.service

import com.cj.smartworker.business.member.port.`in`.SendPhoneApprovalCodeUseCase
import com.cj.smartworker.business.member.port.out.FindPhoneApprovalHistoryPort
import com.cj.smartworker.business.member.port.out.SavePhoneApprovalCodePort
import com.cj.smartworker.business.member.port.out.SendApprovalCodeToPhonePort
import com.cj.smartworker.domain.member.entity.PhoneApproval
import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.Phone
import com.cj.smartworker.domain.util.toKstLocalDateTime
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime

@Service
internal class SendPhoneApprovalCodeService(
    private val findPhoneApprovalHistoryPort: FindPhoneApprovalHistoryPort,
    private val savePhoneApprovalCodePort: SavePhoneApprovalCodePort,
    private val sendApprovalCodeToPhonePort: SendApprovalCodeToPhonePort,
) : SendPhoneApprovalCodeUseCase {
    override fun requestPhoneApproveCode(phone: Phone): String {
        val count = findPhoneApprovalHistoryPort.findPhoneApprovalHistory(
            phone = phone,
            dateTime = LocalDateTime.now().minusMinutes(30),
        )

        if (count >= 5) {
            throw IllegalArgumentException("인증번호 요청 횟수를 초과하였습니다. \n30분 후에 다시 시도해 보세요.")
        }
        PhoneApproval(
            _id = null,
            _approvalCode = ApprovalCode.generateCode(),
            _phone = phone,
            _isApproved = false,
            _createdAt = Instant.now().toKstLocalDateTime(),

        ).let {
            val phoneApproval = savePhoneApprovalCodePort.savePhoneApprovalCode(it)
            sendApprovalCodeToPhonePort.sendApprovalCodeToPhone(
                phone = phoneApproval.phone,
                code = phoneApproval.approvalCode,
            )
            return it.approvalCode.approvalCode
        }
    }
}
