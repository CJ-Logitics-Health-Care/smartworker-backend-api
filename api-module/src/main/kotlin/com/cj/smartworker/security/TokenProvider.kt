package com.cj.smartworker.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TokenProvider(
    @Value("\${jwt.secret}")
    val secret: String,
) {

}
