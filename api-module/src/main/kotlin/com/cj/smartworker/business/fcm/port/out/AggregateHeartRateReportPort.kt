package com.cj.smartworker.business.fcm.port.out

import com.cj.smartworker.business.fcm.dto.response.HeartRateAggregateResponse
import java.time.LocalDateTime

interface AggregateHeartRateReportPort {
    fun aggregate(
        start: LocalDateTime,
        end: LocalDateTime,
        round: Int, // 반올림 값
    ): List<HeartRateAggregateResponse>
}
