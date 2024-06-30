package com.cj.smartworker.dataaccess.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "approve_phone")
class ApprovePhoneJpaEntity(
    @Id
    @Column(name = "phone")
    val phone: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "member_id", nullable = false)
    val memberId: Long,

    @Column(name = "is_approved", nullable = false)
    val isApproved: Boolean,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ApprovePhoneJpaEntity) return false

        if (phone != other.phone) return false

        return true
    }

    override fun hashCode(): Int {
        return phone.hashCode()
    }
}
