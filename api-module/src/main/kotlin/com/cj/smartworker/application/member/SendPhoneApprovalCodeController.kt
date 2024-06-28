package com.cj.smartworker.application.member

import com.cj.smartworker.annotation.WebAdapter
import com.cj.smartworker.application.response.GenericResponse
import com.cj.smartworker.business.member.port.`in`.SendPhoneApprovalCodeUseCase
import com.cj.smartworker.domain.member.valueobject.Phone
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member API", description = "회원 가입, 로그인, 로그아웃, 회원 정보 수정, 회원 탈퇴 등 API 목록입니다.")
@WebAdapter
@RestController
@RequestMapping("/api/v1/member")
internal class SendPhoneApprovalCodeController(
    private val sendPhoneApprovalCodeUseCase: SendPhoneApprovalCodeUseCase,
) {
    @PostMapping("/send/approval-code")
    @Operation(
        summary = "휴대폰 인증 번호 전송",
        description = "휴대폰 인증 번호를 전송합니다.",
        parameters = [
            Parameter(name = "phone", description = "휴대폰 번호", required = true, example = "01012345678 or 010-1234-5678")
        ]
    )
    @ApiResponse(responseCode = "200", description = "인증번호 전송 성공")
    fun sendApprovalCodeToPhone(
        @RequestParam("phone") phone: String,
    ): GenericResponse<Unit> {
        sendPhoneApprovalCodeUseCase.requestPhoneApproveCode(Phone(phone))

        return GenericResponse(
            data = Unit,
            success = true,
            statusCode = HttpStatus.OK.value()
        )
    }
}
