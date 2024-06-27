package com.cj.smartworker.external.sms

import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessageConfiguration(
    private val messageConfigData: MessageConfigData,
) {

    @Bean
    fun messageService(): DefaultMessageService {
        return DefaultMessageService(
            apiKey = messageConfigData.apiKey,
            apiSecretKey = messageConfigData.apiSecret,
            domain = messageConfigData.serviceHome,
        )
    }
}
