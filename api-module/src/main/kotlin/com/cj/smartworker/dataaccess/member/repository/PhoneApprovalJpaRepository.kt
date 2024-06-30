package com.cj.smartworker.dataaccess.member.repository

import com.cj.smartworker.dataaccess.member.entity.PhoneApprovalJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PhoneApprovalJpaRepository: JpaRepository<PhoneApprovalJpaEntity, Long> {
    fun deleteByPhone(phone: String)
}
