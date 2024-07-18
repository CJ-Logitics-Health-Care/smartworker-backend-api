package com.cj.smartworker.business.member.dto.response

import com.cj.smartworker.domain.member.valueobject.Authority
import com.cj.smartworker.domain.member.valueobject.Gender
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "커서 페이징 조회 회원 정보 응답")
data class MemberPagingResponse(
    @Schema(description = "회원 ID")
    val memberId: Long,
    @Schema(description = "성별")
    val gender: Gender,
    @Schema(description = "권한")
    val authorities: Set<Authority>,
    @Schema(description = "태어난 연도")
    val year: Int,
    @Schema(description = "태어난 월")
    val month: Int,
    @Schema(description = "태어난 일")
    val day: Int,
    @Schema(description = "생성일시")
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    val createdAt: LocalDateTime,
    @Schema(description = "로그인 ID")
    var loginId: String,
    @Schema(description = "전화번호")
    var phone: String,
    @Schema(description = "이름")
    var employeeName: String,
)
