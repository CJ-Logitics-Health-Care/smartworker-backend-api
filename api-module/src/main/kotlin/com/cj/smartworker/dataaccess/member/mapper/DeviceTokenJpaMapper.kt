package com.cj.smartworker.dataaccess.member.mapper

import com.cj.smartworker.dataaccess.member.entity.DeviceTokenJpaEntity
import com.cj.smartworker.domain.member.entity.DeviceToken
import com.cj.smartworker.domain.member.valueobject.DeviceTokenId

fun DeviceTokenJpaEntity.toDomain(): DeviceToken = let {
    DeviceToken(
        _deviceTokenId = it.id?.let { id -> DeviceTokenId(id) },
        _memberId = it.memberId,
        _token = it.token,
    )
}

fun DeviceToken.toJpaEntity(): DeviceTokenJpaEntity = let {
    DeviceTokenJpaEntity(
        id = it.deviceTokenId?.deviceTokenId,
        memberId = it.memberId,
        token = it.token,
    )
}
