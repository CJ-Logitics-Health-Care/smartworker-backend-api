package com.cj.smartworker.business.member.port.out

import com.cj.smartworker.domain.member.entity.DeviceToken
import com.cj.smartworker.domain.member.valueobject.MemberId

fun interface FindTokenPort {
    fun findByMemberId(memberId: MemberId): DeviceToken?
}
