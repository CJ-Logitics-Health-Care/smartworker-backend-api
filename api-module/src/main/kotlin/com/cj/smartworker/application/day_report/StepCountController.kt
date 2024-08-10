package com.cj.smartworker.application.day_report

import com.cj.smartworker.annotation.WebAdapter
import com.cj.smartworker.business.day_report.port.`in`.StepCountUseCase
import com.cj.smartworker.security.MemberContext
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.websocket.server.PathParam
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "본선 신기능", description = "본선 신기능.")
@WebAdapter
@RestController
@RequestMapping("/api/v1/reporting")
internal class StepCountController(
    private val stepCountUseCase: StepCountUseCase,
) {

    @PostMapping("/step-count")
    fun sendStepCount(@PathParam("step") step: Long) {
        stepCountUseCase.sendStepCount(
            member = MemberContext.MEMBER,
            step = step,
        )
    }
}
