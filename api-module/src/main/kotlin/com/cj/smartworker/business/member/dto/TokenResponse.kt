package com.cj.smartworker.business.member.dto

data class TokenResponse(
    val token: String,
    val refreshToken: String,
)
