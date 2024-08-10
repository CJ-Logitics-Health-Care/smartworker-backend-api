package com.cj.smartworker.business.day_report.port.out

import com.cj.smartworker.business.day_report.dto.StepCountRequest

interface StepCountMessagePort {
    fun sendStepPublish(stepCount: StepCountRequest)
}
