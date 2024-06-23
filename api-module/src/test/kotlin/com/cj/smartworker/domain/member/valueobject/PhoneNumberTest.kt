package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PhoneNumberTest {
    @Test
    fun `잘못된 휴대폰 번호를 넣은 경우 MemberDomainException 에외를 던진다`() {
        val invalidPhoneNumber = "010-1234-567p"
        assertThrows(MemberDomainException::class.java) {
            PhoneNumber(invalidPhoneNumber)
        }.message.let {
            assertEquals("전화번호는 숫자와 '-'만 가능합니다.", it)
        }
    }

    @Test
    fun `정상적으로 입력한 경우`() {
        val phoneNumber = "010-1234-5678"
        assertDoesNotThrow {
            PhoneNumber(phoneNumber)
        }
    }
}
