package com.cj.smartworker.kafka.model

import java.io.Serializable

data class HeartRate(
    val memberId: Long,
    val heartRate: Int,
    val timestamp: Long,
) : Serializable
