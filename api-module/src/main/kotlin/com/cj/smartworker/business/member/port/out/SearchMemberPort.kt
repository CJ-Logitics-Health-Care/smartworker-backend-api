package com.cj.smartworker.business.member.port.out

import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.LoginId

fun interface SearchMemberPort {
    fun searchByLoginId(loginId: LoginId): Member?
}
