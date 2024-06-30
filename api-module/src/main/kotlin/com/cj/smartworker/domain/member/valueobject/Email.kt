package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException

data class Email(
    val email: String,
) {
    private val message = "잘못된 이메일 형식입니다."
    init {
        val contains = email.contains("@")
        if (!contains) {
            throw MemberDomainException(message)
        }
        if (email.last() == '@') {
            throw MemberDomainException(message)
        }

        if (email.first() == '@') {
            throw MemberDomainException(message)
        }
    }
}
