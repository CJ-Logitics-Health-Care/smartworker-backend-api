package com.cj.smartworker.business.member.service

import com.cj.smartworker.business.member.port.`in`.ApproveCodeUseCase
import com.cj.smartworker.dataaccess.member.entity.PhoneApprovalJpaEntity
import com.cj.smartworker.dataaccess.member.repository.PhoneApprovalJpaRepository
import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.Phone
import com.cj.smartworker.domain.util.toKstLocalDateTime
import com.cj.smartworker.testbase.IntegrationTestBase
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant

class ApproveCodeServiceTest @Autowired constructor(
    private val approveCodeUseCase: ApproveCodeUseCase,
    private val phoneApprovalJpaRepository: PhoneApprovalJpaRepository,
) : IntegrationTestBase() {

    @Test
    fun `인증 코드가 존재하지 않으면 IllegalArgumentException 을 던진다`() {
        Assertions.assertThatThrownBy { approveCodeUseCase.approve(ApprovalCode("123456"), Phone("01012345678")) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("인증번호가 존재하지 않습니다. \n다시 인증번호를 요청해 주세요.")
    }

    @Test
    fun `인증코드 발행 이후 5분 이상 지나면 IllegalArgumentException 예외를 던진다`() {
        val phone = "01012345678"
        val approvalCode = "123456"
        PhoneApprovalJpaEntity(
            id = null,
            phone = phone,
            approvalCode = approvalCode,
            createdAt = Instant.now().toKstLocalDateTime().minusMinutes(5L),
            isApproved = false,
        ).let { phoneApprovalJpaRepository.save(it) }

        Assertions.assertThatThrownBy { approveCodeUseCase.approve(ApprovalCode(approvalCode), Phone(phone)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("인증번호가 만료되었습니다. \n다시 인증번호를 요청해 주세요.")
    }

    @Test
    fun `인증번호가 일치하지 않을 경우 IllegalArgumentException 예외를 던진다`() {
        val phone = "01012345678"
        val approvalCode = "123456"
        PhoneApprovalJpaEntity(
            id = null,
            phone = phone,
            approvalCode = approvalCode,
            createdAt = Instant.now().toKstLocalDateTime(),
            isApproved = false,
        ).let { phoneApprovalJpaRepository.save(it) }

        Assertions.assertThatThrownBy { approveCodeUseCase.approve(ApprovalCode("123123"), Phone(phone)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("인증번호가 일치하지 않습니다.")
    }

    @Test
    fun `정상 저장의 경우 isApproval이 true가 된다 `() {
        val phone = "01012345678"
        val approvalCode = "123456"
        PhoneApprovalJpaEntity(
            id = null,
            phone = phone,
            approvalCode = approvalCode,
            createdAt = Instant.now().toKstLocalDateTime(),
            isApproved = false,
        ).let { phoneApprovalJpaRepository.save(it) }
        approveCodeUseCase.approve(ApprovalCode(approvalCode), Phone(phone))
        phoneApprovalJpaRepository.findAll()[0].let {
            Assertions.assertThat(it.isApproved).isTrue()
        }
    }
}
