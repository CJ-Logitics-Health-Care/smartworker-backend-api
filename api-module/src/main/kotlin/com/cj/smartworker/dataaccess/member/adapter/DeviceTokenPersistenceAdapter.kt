package com.cj.smartworker.dataaccess.member.adapter

import com.cj.smartworker.annotation.PersistenceAdapter
import com.cj.smartworker.business.member.port.out.FindTokenPort
import com.cj.smartworker.business.member.port.out.SaveTokenPort
import com.cj.smartworker.dataaccess.member.mapper.toDomain
import com.cj.smartworker.dataaccess.member.mapper.toJpaEntity
import com.cj.smartworker.dataaccess.member.repository.DeviceTokenJpaRepository
import com.cj.smartworker.domain.member.entity.DeviceToken
import com.cj.smartworker.domain.member.valueobject.MemberId

@PersistenceAdapter
class DeviceTokenPersistenceAdapter(
    private val deviceTokenRepository: DeviceTokenJpaRepository,
): SaveTokenPort, FindTokenPort {
    override fun findByMemberId(memberId: MemberId): DeviceToken? {
        return deviceTokenRepository.findByMemberId(memberId)?.toDomain()
    }

    override fun saveToken(deviceToken: DeviceToken): DeviceToken {
        return deviceTokenRepository.save(deviceToken.toJpaEntity()).toDomain()
    }
}
