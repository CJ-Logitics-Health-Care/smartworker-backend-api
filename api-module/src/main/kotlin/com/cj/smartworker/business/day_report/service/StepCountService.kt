package com.cj.smartworker.business.day_report.service

import com.cj.smartworker.business.day_report.dto.StepCountRequest
import com.cj.smartworker.business.day_report.port.`in`.StepCountUseCase
import com.cj.smartworker.business.day_report.port.out.StepCountMessagePort
import com.cj.smartworker.domain.member.entity.Member
import org.springframework.stereotype.Service

@Service
internal class StepCountService(
    private val stepCountMessagePort: StepCountMessagePort,
): StepCountUseCase {
    override fun sendStepCount(member: Member, step: Long) {
        stepCountMessagePort.sendStepPublish(
            StepCountRequest(
                memberId = member.id!!.id,
                step = step,
            )
        )
    }
}
