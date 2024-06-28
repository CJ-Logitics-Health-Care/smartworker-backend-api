package com.cj.smartworker.external.sms

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class MessageConfigData(
    @Value("\${sms.apiKey}")
    val apiKey: String,
    @Value("\${sms.apiSecret}")
    val apiSecret: String,
    @Value("\${sms.serviceDomain}")
    val serviceHome: String,
)
