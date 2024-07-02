package com.cj.smartworker.business.member.service

import com.cj.smartworker.business.member.dto.response.MemberResponse
import com.cj.smartworker.business.member.mapper.MemberResponseMapper
import com.cj.smartworker.business.member.port.`in`.SearchMemberUseCase
import com.cj.smartworker.business.member.port.out.SearchMemberPort
import com.cj.smartworker.domain.member.valueobject.LoginId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class SearchMemberService(
    private val searchMemberPort: SearchMemberPort,
    private val memberResponseMapper: MemberResponseMapper,
): SearchMemberUseCase {
    @Transactional(readOnly = true)
    override fun searchByLoginId(loginId: LoginId): MemberResponse? {
        val member = searchMemberPort.searchByLoginId(loginId) ?: run {
            return null
        }
        return memberResponseMapper.memberToMemberResponse(member)
    }
}
