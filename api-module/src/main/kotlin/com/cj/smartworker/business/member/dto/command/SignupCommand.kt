package com.cj.smartworker.business.member.dto.command

import com.cj.smartworker.domain.member.valueobject.Gender
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원가입 요청값")
data class SignupCommand(
    @Schema(description = "로그인 아이디")
    val loginId: String,
    @Schema(description = "로그인 비밀번호")
    val password: String,
    @Schema(description = "전화번호")
    val phone: String,
    @Schema(description = "성별")
    val gender: Gender,
    @Schema(description = "이메일")
    val email: String?,
    @Schema(description = "이름")
    val employeeName: String,
    @Schema(description = "나이")
    val age: Int,
)
