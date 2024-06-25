package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException
import com.cj.smartworker.domain.util.PasswordEncodeUtil

/**
 * 비밀빈호 8글자 이상, 영문 숫자 특수문자 모두 사용해야함
 * 특수문자: !@#$%^&*-
 * 중간에 whitespace가 있으면 안됨
 */
data class Password private constructor(
    val password: String,
) {
    companion object {
        operator fun invoke(value: String): Password {
            val password = value.trim()

            if (password.length !in 8..20) {
                throw MemberDomainException("비밀번호의 길이는 8 ~ 20글자여야합니다.")
            }
            // Check if the password contains at least one English letter
            val containsLetter = password.any { it.isLetter() }

            // Check if the password contains at least one digit
            val containsDigit = password.any { it.isDigit() }

            // Check if the password contains at least one special character
            val specialCharacters = "!@#$%^&*-"
            val containsSpecialChar = password.any { it in specialCharacters }

            if (!containsLetter || !containsDigit || !containsSpecialChar) {
                throw MemberDomainException("비밀번호는 영어, 숫자, 특수문자를 포함해야합니다.")
            }
            if (password.contains(" ")) {
                throw MemberDomainException("비밀번호에 공백이 포함되어있습니다.")
            }
            return Password(PasswordEncodeUtil.encode(value))
        }

        fun fromEncoded(encodedPassword: String): Password {
            return Password(encodedPassword)
        }
    }
}
