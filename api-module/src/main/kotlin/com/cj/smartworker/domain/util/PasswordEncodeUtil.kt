package com.cj.smartworker.domain.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class PasswordEncodeUtil {
    companion object {
        private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

        fun encode(password: String): String {
            return passwordEncoder.encode(password)
        }

        fun matches(password: String, encodedPassword: String): Boolean {
            return passwordEncoder.matches(password, encodedPassword)
        }
    }
}
