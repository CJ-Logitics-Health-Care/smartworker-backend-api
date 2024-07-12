package com.cj.smartworker.business.fcm.port.out

fun interface FcmPushPort {
    fun sendMessage(
        targetToken: String,
        title: String,
        body: String,
        x: Int,
        y: Int,
    ): Boolean
}
