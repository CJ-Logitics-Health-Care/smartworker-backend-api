package com.cj.smartworker.business.fcm.service

import com.cj.smartworker.business.fcm.dto.request.GPSRange
import com.cj.smartworker.business.fcm.dto.request.GPSRange.LARGE
import com.cj.smartworker.business.fcm.dto.response.HeartRateAggregateResponse
import com.cj.smartworker.business.fcm.port.`in`.AggregateHeartRateReportUseCase
import com.cj.smartworker.business.fcm.port.out.AggregateHeartRateReportPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
internal class AggregateHeartRateReportService(
    private val aggregateHeartRateReportPort: AggregateHeartRateReportPort,
): AggregateHeartRateReportUseCase {
    override fun aggregate(
        start: LocalDateTime,
        end: LocalDateTime,
        gpsRange: GPSRange,
    ): List<HeartRateAggregateResponse> {
        return aggregateHeartRateReportPort.aggregate(
            start = start,
            end = end,
            gpsRange = gpsRange,
        )
    }
}
