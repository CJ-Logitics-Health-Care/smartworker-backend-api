package com.cj.smartworker.business.member.port.out

import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.LoginId

interface FindMemberPort {
    fun findById(id: Long): Member?

    fun findByLoginId(loginId: LoginId): Member?
}
