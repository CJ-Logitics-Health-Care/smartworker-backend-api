package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException

@JvmInline
value class Age(
    val age: Int
) {
    init {
        if (age < 0) {
            throw MemberDomainException("나이는 0보다 작을 수 없습니다.")
        }
    }
}
