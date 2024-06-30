package com.cj.smartworker.dataaccess.member.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "phone_approval",
    indexes = [
        Index(name = "phone_approval_phone_index", columnList = "phone"),
    ],
)
class PhoneApprovalJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_approval_id")
    val id: Long?,

    @Column(name = "phone")
    val phone: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "approval_code", columnDefinition = "varchar(7)")
    val approvalCode: String,

    @Column(name = "is_approved", nullable = false)
    val isApproved: Boolean,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneApprovalJpaEntity) return false

        if (phone != other.phone) return false

        return true
    }

    override fun hashCode(): Int {
        return phone.hashCode()
    }
}
