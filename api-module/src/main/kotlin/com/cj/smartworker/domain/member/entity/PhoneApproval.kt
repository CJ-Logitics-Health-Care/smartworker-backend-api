package com.cj.smartworker.domain.member.entity

import com.cj.smartworker.domain.common.BaseEntity
import com.cj.smartworker.domain.member.valueobject.ApprovalCode
import com.cj.smartworker.domain.member.valueobject.Phone
import com.cj.smartworker.domain.member.valueobject.PhoneApprovalId
import java.time.LocalDateTime

data class PhoneApproval(
    private val _id: PhoneApprovalId?,
    private val _phone: Phone,
    private val _approvalCode: ApprovalCode,
    private val _isApproved: Boolean,
    private val _createdAt: LocalDateTime,
): BaseEntity<PhoneApprovalId?>(_id) {
    override val id: PhoneApprovalId?
        get() = _id
    val phone: Phone
        get() = _phone
    val approvalCode: ApprovalCode
        get() = _approvalCode
    val isApproved: Boolean
        get() = _isApproved
    val createdAt: LocalDateTime
        get() = _createdAt
    fun approve(): PhoneApproval {
        return this.copy(_isApproved = true)
    }
}
