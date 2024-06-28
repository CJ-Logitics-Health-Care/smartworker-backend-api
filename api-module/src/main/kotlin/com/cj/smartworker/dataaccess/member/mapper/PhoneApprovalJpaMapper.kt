package com.cj.smartworker.dataaccess.member.mapper

import com.cj.smartworker.dataaccess.member.entity.PhoneApprovalJpaEntity
import com.cj.smartworker.domain.member.entity.PhoneApproval
import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.Phone
import com.cj.smartworker.domain.member.valueobject.PhoneApprovalId

fun PhoneApproval.toJpaEntity() = PhoneApprovalJpaEntity(
    id = id?.id,
    phone = phone.phone,
    approvalCode = this.approvalCode.approvalCode,
    createdAt = createdAt,
    isApproved = isApproved,
)

fun PhoneApprovalJpaEntity.toDomainEntity() = PhoneApproval(
    _id = id?.let { PhoneApprovalId(it) },
    _phone = Phone(phone),
    _approvalCode = ApprovalCode(approvalCode),
    _isApproved = isApproved,
    _createdAt = createdAt,
)
