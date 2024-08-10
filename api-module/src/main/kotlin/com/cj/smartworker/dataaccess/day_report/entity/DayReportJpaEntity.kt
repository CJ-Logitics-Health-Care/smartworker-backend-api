package com.cj.smartworker.dataaccess.day_report.entity

import com.cj.smartworker.dataaccess.member.entity.MemberJpaEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "day_report")
class DayReportJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @OneToOne
    @JoinColumn(name = "member_id")
    val memberJpaEntity: MemberJpaEntity,

    @Column(name = "member_name")
    val memberName: String,

    @Column(name = "move_work")
    val moveWork: Int, // 걸음 수

    @Column(name = "heart_rate")
    val heartRate: Double, // 심박수

    @Column(name = "km")
    val km: Double, // 이동거리 km 단위

    @Column(name = "created_at")
    val createdAt: LocalDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DayReportJpaEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
