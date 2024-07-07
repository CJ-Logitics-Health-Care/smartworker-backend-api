package com.cj.smartworker.application.fcm

import com.cj.smartworker.annotation.WebAdapter
import com.cj.smartworker.application.response.GenericResponse
import com.cj.smartworker.business.fcm.port.`in`.EmergencyAlarmPushUseCase
import com.cj.smartworker.domain.member.valueobject.EmployeeName
import com.cj.smartworker.security.MemberContext.MEMBER
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "FCM API", description = "FCM 푸시 등 API 목록입니다.")
@WebAdapter
@RestController
@RequestMapping("/api/v1/fcm")
internal class FcmPushController(
    private val emergencyAlarmPushUseCase: EmergencyAlarmPushUseCase,
) {

    @Operation(summary = "긴급 알람 푸시", description = "긴급 알람 푸시를 전송합니다.")
    @ApiResponse(responseCode = "200", description = "푸시 성공. 관리자 이름 목록 반환.")
    @PostMapping("/emergency-alarm")
    fun emergencyAlarmPush(): GenericResponse<List<EmployeeName>> {
        val adminNames = emergencyAlarmPushUseCase.pushEmergencyAlarm(MEMBER)

        return GenericResponse(
            data = adminNames,
            success = true,
            statusCode = HttpStatus.OK.value(),
        )
    }
}
