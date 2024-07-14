package com.cj.smartworker.kafka.producer

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class KafkaMessagingHelper(
    private val objectMapper: ObjectMapper,
) {
    fun <T> getEventPayload(
        payload: String,
        outputType: Class<T>,
    ): T {
        try {
            return objectMapper.readValue(payload, outputType)
        } catch (e: JsonProcessingException) {
            throw RuntimeException("Kafka Producer JSON 파싱 실패 ${outputType.name}")
        }
    }

}
