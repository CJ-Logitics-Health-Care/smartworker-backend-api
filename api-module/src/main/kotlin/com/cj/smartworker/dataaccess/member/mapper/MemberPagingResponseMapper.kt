package com.cj.smartworker.dataaccess.member.mapper

import com.cj.smartworker.business.member.dto.response.MemberPagingResponse
import com.cj.smartworker.dataaccess.member.entity.MemberJpaEntity

fun MemberJpaEntity.toMemberPagingResponse(): MemberPagingResponse = let {
    MemberPagingResponse(
        memberId = it.id!!,
        createdAt = it.createdAt,
        gender = it.gender,
        authorities = it.authorities.map { authority -> authority.authority }.toSet(),
        year = it.year,
        month = it.month,
        day = it.day,
        loginId = it.loginId,
        phone = it.phone,
        employeeName = it.employeeName,
    )
}
