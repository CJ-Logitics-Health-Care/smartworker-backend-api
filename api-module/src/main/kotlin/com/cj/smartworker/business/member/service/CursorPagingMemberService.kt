package com.cj.smartworker.business.member.service

import com.cj.smartworker.business.common.dto.request.CursorRequest
import com.cj.smartworker.business.common.dto.response.CursorResultResponse
import com.cj.smartworker.business.member.dto.response.MemberPagingResponse
import com.cj.smartworker.business.member.port.`in`.CursorPagingMemberUseCase
import com.cj.smartworker.business.member.port.out.HasNextMemberPort
import com.cj.smartworker.business.member.port.out.PagingMemberPort
import com.cj.smartworker.domain.member.valueobject.MemberId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class CursorPagingMemberService(
    private val pagingMemberPort: PagingMemberPort,
    private val hasNextMemberPort: HasNextMemberPort,
): CursorPagingMemberUseCase {
    @Transactional(readOnly = true)
    override fun findMembers(cursorRequest: CursorRequest): CursorResultResponse<MemberPagingResponse> {
        pagingMemberPort.pagingMembers(cursorRequest).let { members ->
            val lastIndex = if (members.isEmpty()) null else members.last().memberId

            val hasNext = if (lastIndex != null){
                hasNextMemberPort.hasNext(MemberId(members.last().memberId))
            } else {
                false
            }
            return CursorResultResponse(members, hasNext, lastIndex)
        }
    }
}
