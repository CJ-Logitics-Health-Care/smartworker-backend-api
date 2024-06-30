package com.cj.smartworker.domain.member.valueobject

@JvmInline
value class Phone(val phone: String) {
    init {
        require(phone.matches(PHONE_REGEX)) {
            "전화번호는 숫자와 '-'만 가능합니다."
        }
    }

    companion object {
        private val PHONE_REGEX = "^[0-9-]+$".toRegex()
    }
}
