package com.cj.smartworker.application.fcm

import com.cj.smartworker.annotation.WebAdapter
import com.cj.smartworker.application.response.GenericResponse
import com.cj.smartworker.business.fcm.dto.response.EmergencyReportDto
import com.cj.smartworker.business.fcm.port.`in`.FindEmergencyReportUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime

@Tag(name = "FCM API", description = "FCM 푸시, 히스토리 등 API 목록입니다.")
@WebAdapter
@RestController
@RequestMapping("/api/v1/fcm")
internal class EmergencyReportController(
    private val emergencyReportUseCase: FindEmergencyReportUseCase,
) {

    @Operation(
        summary = "신고 이력 조회",
        description = "신고 이력을 조회합니다. [Admin]",
        parameters = [
            Parameter(name = "start", description = "신고 이력 조회 시작 시간", required = true),
            Parameter(name = "end", description = "신고 이력 조회 끝나는 시간", required = true),
            Parameter(name = "emergency", description = "HEART_RATE or REPORT", required = true),
        ]
    )
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "200", description = "푸시 성공. 관리자 이름 목록 반환.")
    @GetMapping("/admin/emergency-report")
    fun emergencyReport(
        @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") start: LocalDate,
        @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") end: LocalDate,
    ): GenericResponse<List<EmergencyReportDto>> {
        val report = emergencyReportUseCase.findReport(
            start = start.atStartOfDay(),
            end = end.atTime(23, 59, 59),
        )
        return GenericResponse(
            data = report,
            success = true,
            statusCode = HttpStatus.OK.value(),
        )
    }
}
