package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException

@JvmInline
value class Email(
    val email: String,
) {

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
private const val message = "잘못된 이메일 형식입니다."
