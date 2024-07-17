package com.cj.smartworker.dataaccess.fcm.repository

import com.cj.smartworker.dataaccess.fcm.entity.FcmHistoryJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface FcmHistoryJpaRepository: JpaRepository<FcmHistoryJpaEntity, Long> {

    // start end 사이의 신고 이력
    fun findByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): List<FcmHistoryJpaEntity>
}
