package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException

/**
 * 아이디 4~12 글자, 영어와 숫자 조합만 가능
 */
@JvmInline
value class LoginId(
    val loginId: String,
) {

    init {
        if (loginId.length !in 4..12) {
            throw MemberDomainException("아이디의 길이는 4 ~ 12글자여야합니다.")
        }
        if (!regex.matches(loginId)) {
            throw MemberDomainException("아이디는 영어와 숫자 조합만 가능합니다.")
        }

        loginId.any { it.isLetter() }.and(loginId.any { it.isDigit() }).let { isLetterAndDigit ->
            if (!isLetterAndDigit) {
                throw MemberDomainException("아이디는 영어와 숫자 조합만 가능합니다.")
            }
        }
    }
}
private val regex = "^[a-zA-Z0-9]*$".toRegex()
