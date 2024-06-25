package com.cj.smartworker.business.member.port.`in`

import com.cj.smartworker.business.member.dto.command.SignupCommand

fun interface SignupUseCase {
    fun signup(command: SignupCommand)
}
