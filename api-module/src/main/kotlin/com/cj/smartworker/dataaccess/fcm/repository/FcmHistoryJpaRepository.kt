package com.cj.smartworker.dataaccess.fcm.repository

import com.cj.smartworker.dataaccess.fcm.entity.FcmHistoryJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FcmHistoryJpaRepository: JpaRepository<FcmHistoryJpaEntity, Long> {
}
