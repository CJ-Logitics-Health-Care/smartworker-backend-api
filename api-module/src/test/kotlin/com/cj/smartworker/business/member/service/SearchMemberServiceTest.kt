package com.cj.smartworker.business.member.service

import com.cj.smartworker.business.member.port.`in`.SearchMemberUseCase
import com.cj.smartworker.business.member.port.`in`.SignupUseCase
import com.cj.smartworker.dataaccess.member.entity.PhoneApprovalJpaEntity
import com.cj.smartworker.dataaccess.member.repository.PhoneApprovalJpaRepository
import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.LoginId
import com.cj.smartworker.domain.util.toKstLocalDateTime
import com.cj.smartworker.testbase.IntegrationTestBase
import com.cj.smartworker.fixture.MemberFixture
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant

class SearchMemberServiceTest @Autowired constructor(
    private val searchMemberUseCase: SearchMemberUseCase,
    private val signupUseCase: SignupUseCase,
    private val phoneApprovalJpaRepository: PhoneApprovalJpaRepository,
): IntegrationTestBase() {
    @Test
    fun `해당 loginId의 유저가 존재하면 찾은 유저의 정보를 반환한다`() {
        val signupCommand = MemberFixture.createSignupCommand()
        PhoneApprovalJpaEntity(
            id = null,
            phone = signupCommand.phone,
            createdAt = Instant.now().toKstLocalDateTime(),
            approvalCode = ApprovalCode.generateCode().approvalCode,
            isApproved = true,
        ).let {
            phoneApprovalJpaRepository.save(it)
        }
        signupUseCase.signup(signupCommand)
        val loginId = signupCommand.loginId
        val memberResponse = searchMemberUseCase.searchByLoginId(LoginId(loginId))
        assertEquals(signupCommand.employeeName, memberResponse!!.name)
    }
}
