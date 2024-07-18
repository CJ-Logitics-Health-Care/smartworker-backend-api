package com.cj.smartworker.dataaccess.fcm.repository

import com.cj.smartworker.dataaccess.fcm.entity.FcmHistoryJpaEntity
import com.cj.smartworker.dataaccess.member.entity.MemberJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface FcmHistoryJpaRepository: JpaRepository<FcmHistoryJpaEntity, Long> {

    // start end 사이의 신고 이력
    fun findByCreatedAtBetweenOrderByCreatedAtDesc(start: LocalDateTime, end: LocalDateTime): List<FcmHistoryJpaEntity>

    fun findByReporterOrderByCreatedAtDesc(memberJapEntity: MemberJpaEntity): List<FcmHistoryJpaEntity>
}
