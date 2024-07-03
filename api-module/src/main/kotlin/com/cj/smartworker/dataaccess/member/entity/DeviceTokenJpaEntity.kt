package com.cj.smartworker.dataaccess.member.entity

import com.cj.smartworker.domain.member.valueobject.MemberId
import com.cj.smartworker.domain.member.valueobject.Token
import jakarta.persistence.*

@Entity
@Table(name = "device_token")
class DeviceTokenJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "member_id")
    val memberId: MemberId,

    @Column(name = "token")
    val token: Token,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DeviceTokenJpaEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
