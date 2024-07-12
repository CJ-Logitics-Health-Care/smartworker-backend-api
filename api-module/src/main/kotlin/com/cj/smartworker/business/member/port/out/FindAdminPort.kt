package com.cj.smartworker.business.member.port.out

import com.cj.smartworker.domain.member.entity.Member


fun interface FindAdminPort {
    fun findAdmins(): List<Member>
}
