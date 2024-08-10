package com.cj.smartworker.business.day_report.dto

import java.io.Serializable

data class StepCountRequest(
    val memberId: Long,
    val step: Long,
): Serializable
