package com.cj.smartworker.business.member.service

import com.cj.smartworker.business.member.port.out.FindPhoneApprovalHistoryPort
import com.cj.smartworker.business.member.port.out.SavePhoneApprovalCodePort
import com.cj.smartworker.business.member.port.out.SendApprovalCodeToPhonePort
import com.cj.smartworker.domain.member.valueobject.Phone
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


// Unit Test로 진행
class SendPhoneApprovalCodeServiceTest {
    private val findPhoneApprovalHistoryPort: FindPhoneApprovalHistoryPort = mockk()
    private val savePhoneApprovalCodePort: SavePhoneApprovalCodePort = mockk()
    private val sendApprovalCodeToPhonePort: SendApprovalCodeToPhonePort = mockk()

    private val sendPhoneApprovalCodeService: SendPhoneApprovalCodeService = SendPhoneApprovalCodeService(
        findPhoneApprovalHistoryPort,
        savePhoneApprovalCodePort,
        sendApprovalCodeToPhonePort,
    )

    @Test
    fun `send history가 5회 이상이면 IllegalArgumentException 예외를 던진다`() {
        // given
        val phone = Phone("01044745825")

        every {
            findPhoneApprovalHistoryPort.findPhoneApprovalHistory(phone, any())
        }.returns(5)

        Assertions.assertThatThrownBy { sendPhoneApprovalCodeService.requestPhoneApproveCode(phone) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("인증번호 요청 횟수를 초과하였습니다. \n30분 후에 다시 시도해 보세요.")
    }
}
