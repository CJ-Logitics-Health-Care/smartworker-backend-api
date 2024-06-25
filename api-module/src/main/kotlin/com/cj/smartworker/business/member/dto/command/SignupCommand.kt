package com.cj.smartworker.business.member.dto.command

import com.cj.smartworker.domain.member.valueobject.Gender

data class SignupCommand(
    val loginId: String,
    val password: String,
    val phone: String,
    val gender: Gender,
    val email: String?,
    val employeeName: String,
)
