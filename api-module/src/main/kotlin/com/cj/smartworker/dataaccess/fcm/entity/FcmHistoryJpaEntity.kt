package com.cj.smartworker.dataaccess.fcm.entity

import com.cj.smartworker.dataaccess.member.entity.MemberJpaEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "fcm_history")
@Entity
class FcmHistoryJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fcm_history_id", nullable = false, unique = true)
    val id: Long?,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "fcm_history_admin",
        joinColumns = [JoinColumn(name = "fcm_history_id")],
        inverseJoinColumns = [JoinColumn(name = "member_id")],
    )
    val admins: Set<MemberJpaEntity>, // 알람을 받은 관리자들

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    val reporter: MemberJpaEntity, // 알람을 보낸 사용자
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FcmHistoryJpaEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
