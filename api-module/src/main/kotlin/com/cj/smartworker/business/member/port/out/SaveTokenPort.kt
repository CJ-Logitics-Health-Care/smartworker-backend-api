package com.cj.smartworker.business.member.port.out

import com.cj.smartworker.domain.member.entity.DeviceToken

fun interface SaveTokenPort {
    fun saveToken(deviceToken: DeviceToken): DeviceToken
}
