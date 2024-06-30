package com.cj.smartworker.application.member

import com.cj.smartworker.annotation.WebAdapter
import com.cj.smartworker.application.response.GenericResponse
import com.cj.smartworker.business.member.port.`in`.ApproveCodeUseCase
import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.Phone
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member API", description = "회원 가입, 로그인, 로그아웃, 회원 정보 수정, 회원 탈퇴 등 API 목록입니다.")
@WebAdapter
@RestController
@RequestMapping("/api/v1/member")
internal class ApproveController(
    private val approveCodeUseCase: ApproveCodeUseCase,
) {
    @Operation(
        summary = "인증코드 체크",
        description = "인증코드를 체크합니다.\n 성공시 true, 실패시 false를 반환합니다.",
        parameters = [
            Parameter(name = "approvalCode", description = "인증코드", required = true, example = "123456"),
            Parameter(name = "phone", description = "휴대폰 번호", required = true, example = "010-1234-5678")
        ]
    )
    @ApiResponse(responseCode = "200", description = "인증 성공")
    @PutMapping("/approve")
    fun approvePhone(
        @RequestParam approvalCode: ApprovalCode,
        @RequestParam phone: Phone,
    ): GenericResponse<Boolean> {
        val approve = approveCodeUseCase.approve(
            approvalCode = approvalCode,
            phone = phone,
        )
        return GenericResponse(
            data = approve,
            statusCode = HttpStatus.OK.value(),
            success = true,
        )
    }
}
