package com.cj.smartworker.dataaccess.member.adapter

import com.cj.smartworker.annotation.PersistenceAdapter
import com.cj.smartworker.business.member.port.out.FindPhoneApprovalHistoryPort
import com.cj.smartworker.business.member.port.out.SavePhoneApprovalCodePort
import com.cj.smartworker.dataaccess.member.entity.QPhoneApprovalJpaEntity.*
import com.cj.smartworker.dataaccess.member.mapper.toDomainEntity
import com.cj.smartworker.dataaccess.member.mapper.toJpaEntity
import com.cj.smartworker.dataaccess.member.repository.PhoneApprovalJpaRepository
import com.cj.smartworker.domain.member.entity.PhoneApproval
import com.cj.smartworker.domain.member.valueobject.Phone
import com.querydsl.jpa.impl.JPAQueryFactory
import java.time.LocalDateTime

@PersistenceAdapter
class PhoneApprovalAdapter(
    private val phoneApprovalJpaRepository: PhoneApprovalJpaRepository,
    private val queryFactory: JPAQueryFactory,
): FindPhoneApprovalHistoryPort,
    SavePhoneApprovalCodePort {
    override fun findPhoneApprovalHistory(phone: Phone, dateTime: LocalDateTime): Int {
        val fetch = queryFactory.select(phoneApprovalJpaEntity.id.count())
            .from(phoneApprovalJpaEntity)
            .where(
                phoneApprovalJpaEntity.phone.eq(phone.phone),
                phoneApprovalJpaEntity.createdAt.after(dateTime)
            )
            .fetchOne()!!
        return fetch.toInt()
    }

    override fun savePhoneApprovalCode(phoneApproval: PhoneApproval): PhoneApproval {
        val save = phoneApprovalJpaRepository.save(phoneApproval.toJpaEntity())
        return save.toDomainEntity()
    }
}
