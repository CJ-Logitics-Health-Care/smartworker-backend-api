package com.cj.smartworker.domain.member.entity

import com.cj.smartworker.domain.common.BaseEntity
import com.cj.smartworker.domain.member.valueobject.DeviceTokenId
import com.cj.smartworker.domain.member.valueobject.MemberId
import com.cj.smartworker.domain.member.valueobject.Token

class DeviceToken(
    private val _deviceTokenId: DeviceTokenId?,
    private val _memberId: MemberId,
    private var _token: Token,
): BaseEntity<DeviceTokenId?>(_deviceTokenId){
    val deviceTokenId: DeviceTokenId?
        get() = _deviceTokenId
    val memberId: MemberId
        get() = _memberId

    val token: Token
        get() = _token

    fun updateToken(token: Token): DeviceToken {
        _token = token
        return this
    }
}
