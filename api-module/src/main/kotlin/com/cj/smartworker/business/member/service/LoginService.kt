package com.cj.smartworker.business.member.service

import com.cj.smartworker.business.member.dto.TokenResponse
import com.cj.smartworker.business.member.dto.request.LoginRequest
import com.cj.smartworker.business.member.port.`in`.LoginUseCase
import com.cj.smartworker.business.member.port.out.FindMemberPort
import com.cj.smartworker.domain.member.exception.MemberDomainException
import com.cj.smartworker.domain.member.valueobject.LoginId
import com.cj.smartworker.domain.util.PasswordEncodeUtil
import com.cj.smartworker.security.TokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class LoginService(
    private val findMemberPort: FindMemberPort,
    private val tokenProvider: TokenProvider,
): LoginUseCase {

    @Transactional(readOnly = true)
    override fun login(loginRequest: LoginRequest): TokenResponse {
        val member = findMemberPort.findByLoginId(LoginId(loginRequest.loginId)) ?: run {
            throw MemberDomainException("멤버십을 찾지 못하였습니다")
        }
        val matches = PasswordEncodeUtil.matches(loginRequest.password, member.password.password)
        if (matches.not()) {
            throw MemberDomainException("패스워드가 일치하지 않습니다")
        }

        return TokenResponse(
            token = tokenProvider.createToken(member),
            refreshToken = tokenProvider.createRefreshToken(member),
        )
    }
}
