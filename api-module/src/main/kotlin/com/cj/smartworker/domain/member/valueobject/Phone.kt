package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException

@JvmInline
value class Phone(
    val phone: String,
) {
    init {
        val regex = "^[0-9-]+$".toRegex()

        if (!regex.matches(phone)) {
            throw MemberDomainException("전화번호는 숫자와 '-'만 가능합니다.")
        }
    }
}
