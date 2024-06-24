package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException

data class EmployeeName(
    val employeeName: String,
) {
    init {
        if (employeeName.length !in 2..20) {
            throw MemberDomainException("직원 이름은 2~20자여야 합니다.")
        }
    }
}
