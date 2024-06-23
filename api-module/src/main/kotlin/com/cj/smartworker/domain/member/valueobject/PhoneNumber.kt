package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException

data class PhoneNumber(
    val phoneNumber: String,
) {
    init {
        val regex = "^[0-9-]+$".toRegex()

        if (!regex.matches(phoneNumber)) {
            throw MemberDomainException("전화번호는 숫자와 '-'만 가능합니다.")
        }
    }
}
