package com.cj.smartworker.dataaccess.fcm.adapter

import com.cj.smartworker.annotation.PersistenceAdapter
import com.cj.smartworker.business.fcm.port.out.FindTokenPort
import com.cj.smartworker.business.fcm.port.out.SaveTokenPort
import com.cj.smartworker.dataaccess.fcm.mapper.toDomain
import com.cj.smartworker.dataaccess.fcm.mapper.toJpaEntity
import com.cj.smartworker.dataaccess.fcm.repository.DeviceTokenJpaRepository
import com.cj.smartworker.dataaccess.member.repository.MemberJpaRepository
import com.cj.smartworker.domain.fcm.entity.DeviceToken
import com.cj.smartworker.domain.fcm.valueobject.Token
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.MemberId
import org.springframework.data.repository.findByIdOrNull

@PersistenceAdapter
class DeviceTokenPersistenceAdapter(
    private val deviceTokenRepository: DeviceTokenJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
): SaveTokenPort,
    FindTokenPort {
    override fun findByMemberIds(memberIds: List<MemberId>): Map<Token, Member> {
        val findByMemberIdIn = deviceTokenRepository.findByMemberIdIn(memberIds)
        return findByMemberIdIn.associate {
            it.token to it.toDomain().member
        }
    }

    override fun findByMemberId(memberId: MemberId): DeviceToken? {
        return deviceTokenRepository.findByMemberId(memberId)?.toDomain()
    }

    override fun saveToken(deviceToken: DeviceToken): DeviceToken {
        val memberJpaEntity = memberJpaRepository.findByIdOrNull(deviceToken.member.memberId!!.id)
            ?: throw IllegalArgumentException("유저를 찾지 못하였습니다.")
        return deviceTokenRepository.save(deviceToken.toJpaEntity()).toDomain()
    }
}
