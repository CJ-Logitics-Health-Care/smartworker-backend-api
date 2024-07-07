package com.cj.smartworker.fcm.dto

data class FcmMessage(
    val validateOnly: Boolean = false,
    val message: Message,
)
