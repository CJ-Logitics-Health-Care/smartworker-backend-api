package com.cj.smartworker.fcm.dto

data class Message(
    val notification: Notification,
    val token: String,
)
