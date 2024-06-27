package com.cj.smartworker.external.sms

import com.cj.smartworker.annotation.ExternalAdapter
import com.cj.smartworker.business.member.port.out.SendApprovalCodeToPhonePort
import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.Phone
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.beans.factory.annotation.Value

@ExternalAdapter
internal class SMSAdapter(
    private val messageService: DefaultMessageService,
    @Value("\${sms.from}")
    val from: String,
): SendApprovalCodeToPhonePort {

    /**
     * 단일 메시지 발행
     */
    override fun sendApprovalCodeToPhone(phone: Phone, code: ApprovalCode) {
        // phone에서 -를 제거한다.
        val toPhone = phone.phone.replace("-", "")

        val message = Message(
            from = from,
            to = toPhone,
            text = "인증번호는 ${code.approvalCode} 입니다."
        )
        messageService.sendOne(SingleMessageSendingRequest(message))
    }
}
