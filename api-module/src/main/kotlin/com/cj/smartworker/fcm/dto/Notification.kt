package com.cj.smartworker.fcm.dto

data class Notification(
    var title: String,
    var body: String,
    var image: String? = null,
)
