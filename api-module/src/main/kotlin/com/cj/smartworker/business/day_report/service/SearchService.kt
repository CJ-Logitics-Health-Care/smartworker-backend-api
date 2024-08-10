package com.cj.smartworker.business.day_report.service

import com.cj.smartworker.business.day_report.dto.response.DayReportResponse
import com.cj.smartworker.business.day_report.port.out.SearchByNamePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchService(
    private val searchByNamePort: SearchByNamePort,
) {

    @Transactional(readOnly = true)
    fun searchByName(name: String): DayReportResponse {
        return searchByNamePort.searchByName(name) ?: run {
            throw IllegalArgumentException("해당 이름의 사원은 존재하지 않습니다.")
        }
    }
}
