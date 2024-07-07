package com.cj.smartworker.dataaccess.fcm.adapter

import com.cj.smartworker.annotation.PersistenceAdapter
import com.cj.smartworker.business.fcm.port.out.SaveFcmHistoryPort
import com.cj.smartworker.dataaccess.fcm.entity.FcmHistoryJpaEntity
import com.cj.smartworker.dataaccess.fcm.repository.FcmHistoryJpaRepository
import com.cj.smartworker.dataaccess.member.mapper.toJpaEntity
import com.cj.smartworker.dataaccess.member.repository.AuthorityJpaRepository
import com.cj.smartworker.domain.member.entity.Member
import java.time.LocalDateTime

@PersistenceAdapter
internal class FcmHistoryPersistenceAdapter(
    private val fcmHistoryJpaRepository: FcmHistoryJpaRepository,
    private val authorityJpaRepository: AuthorityJpaRepository,
): SaveFcmHistoryPort {
    override fun saveFcmHistory(reporter: Member, admins: Set<Member>, createdAt: LocalDateTime) {
        FcmHistoryJpaEntity(
            id = null,
            createdAt = createdAt,
            reporter = reporter.toJpaEntity(),
            admins = admins.map { it.toJpaEntity() }.toSet(),
        )
        TODO("Not yet implemented")
    }
}
