package com.cj.smartworker.business.fcm.dto.response

import com.cj.smartworker.domain.fcm.valueobject.Emergency
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "긴급 신고 정보")
data class EmergencyReportDto(
    @Schema(description = "긴급 신고 ID")
    val id: Long,
    @Schema(description = "신고 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime,
    @Schema(description = "신고자")
    val reporter: String,
    @Schema(description = "긴급 신고 위치 x 좌표")
    val x: Float,
    @Schema(description = "긴급 신고 위치 y 좌표")
    val y: Float,
    @Schema(description = "긴급 신고 사유")
    val emergency: Emergency,
)
